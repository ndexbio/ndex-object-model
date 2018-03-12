package org.cxio.core;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.cxio.aspects.datamodels.CyVisualPropertiesElement;
import org.cxio.aspects.datamodels.HiddenAttributesElement;
import org.cxio.aspects.datamodels.NetworkRelationsElement;
import org.cxio.aspects.datamodels.SubNetworkElement;
import org.cxio.core.interfaces.AspectElement;
import org.cxio.core.interfaces.AspectFragmentReader;
import org.cxio.metadata.MetaDataCollection;
import org.cxio.metadata.MetaDataElement;
import org.cxio.misc.NumberVerification;
import org.cxio.misc.OpaqueFragmentReader;
import org.cxio.misc.Status;
import org.cxio.util.CxConstants;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * CxElementReader2 is used to read aspect elements from an
 * input stream. It is a modified version from CXElementReader class to avoid some bugs in it. Use this instead of CXElementReader class.
 *
 * @author Jing Chen
 *
 */
public final class CxElementReader2 implements Iterable<AspectElement> {
	
	
	//Define states here 
	private final static int START = 0;
	private final static int ASPECT_FRAGMENT = 1;
//	private final static int ASPECT_FRAGMENT_END = 2;
	private final static int END = 3;
	private final static int ELEMENT_LIST_START = 4;
	private final static int IN_ELEMENT_LIST = 5;
	private final static int ELEMENT_LIST_END = 6;
	
	private boolean preMetaDataReceived;
	
    private AspectElement                               _current;
    private HashMap<String, AspectFragmentReader> _element_readers;
    private JsonParser                                  jp;
    private int                                         state;
    private ObjectMapper                                _m;
    private AspectFragmentReader                        _reader;

    MetaDataCollection  _pre_meta_data;
    MetaDataCollection  _post_meta_data;
    Status              _status;
    MessageDigest       _md;
    boolean             _encountered_non_meta_content;
    private boolean compatibleToOldCytoscapeAspect;
    
    /**
     * This creates a new CxElementReader with all AspectFragmentReaders implemented in this library already added.
     *
     * @param input the input object to parse
     * @param read_anonymous_aspect_fragments to enable reading of anonymous aspect fragments
     * @return a CxElementReader
     * @throws IOException
     */
    public CxElementReader2 (InputStream input, Set<AspectFragmentReader> aspect_readers, boolean compatibleToOldCytoscapeAspect) throws IOException {
          
          _element_readers = new HashMap<>();
          this.compatibleToOldCytoscapeAspect = compatibleToOldCytoscapeAspect;
          if (aspect_readers != null) {
              for (final AspectFragmentReader aspect_reader : aspect_readers) {
            	  _element_readers.put(aspect_reader.getAspectName(), aspect_reader);
            	  
            	  // special handling for back compatibility of cytoscape aspects: 
            	  if(compatibleToOldCytoscapeAspect) {
            		  switch (aspect_reader.getAspectName()) {
            		  case CyVisualPropertiesElement.ASPECT_NAME:
            			  _element_readers.put("visualProperties", aspect_reader);
            			  break;
            		  case SubNetworkElement.ASPECT_NAME:
            			  _element_readers.put("subNetworks", aspect_reader);
            			  break;
            		  case NetworkRelationsElement.ASPECT_NAME:
            			  _element_readers.put("networkRelations", aspect_reader);
            			  break;
            		  case HiddenAttributesElement.ASPECT_NAME:
            			  _element_readers.put("hiddenAttributes", aspect_reader);
            			  break;
            		  default:
                		 break;
            	  }
            	  }
              }
          } 
          
          _encountered_non_meta_content = false;
          _pre_meta_data = null;
          _post_meta_data = null;
          state = 0;

          _current = null;
          final JsonFactory f = new JsonFactory();
          jp = f.createParser(input);
          _m = new ObjectMapper();
          preMetaDataReceived = false;
          
          init();
    
    }
    
    // read number verification
    private void init() throws JsonParseException, IOException {
    	 if (jp.nextToken() != JsonToken.START_ARRAY) {
             throw new IllegalStateException("illegal cx json format: expected to start with an array, but has: " + jp.getCurrentToken().asString());
         }
    	  	 
    	 readNumberVerification(); 	 
   }

    
    private String getPosition() {
    	return " line: " + jp.getCurrentLocation().getLineNr() + ", column: "
				+ jp.getCurrentLocation().getColumnNr();
    }
    
    /**
     * This returns one aspect element and advances to the reader to the
     * next aspect element.
     *
     * @return one aspect element, null if end of stream has been reached
     */
    private final AspectElement getNext() {
    	if ( _current != null) {
    		AspectElement result = _current;
    		_current = null;
    		return result;
    	}
    	
    	throw new NoSuchElementException(); 
    }

    /**
     * Returns true if more aspect elements can be read in. Note: If the element has not been read by the getNext() function, this function will not move to check the 
     * next element, so make sure you call the getNext() after each 
     *
     * @return true if more aspect elements can be read in
     * @throws IOException
     */
	private final boolean hasNext() throws IOException {
		if (_current != null)
			return true;
		while (jp.nextToken() != null) {
			JsonToken token = jp.getCurrentToken();
			if (state == START) {
				if (token == JsonToken.END_ARRAY) { // End of Aspect fragment list.
					if (_status == null)
						throw new IOException("CX document ends without a Status object defined at " + getPosition());
					break;
				}
				if (token != JsonToken.START_OBJECT) {
					throw new IOException("Expecting new aspect fragment at" + getPosition());
				}
				state = ASPECT_FRAGMENT;
			} else if (state == ASPECT_FRAGMENT) {
				if (token != JsonToken.FIELD_NAME)
					throw new IOException("Expecting aspect name as a field name at " + getPosition());

				String aspectName = jp.getCurrentName();
				if (aspectName.equals(MetaDataCollection.NAME)) { // MetaDataCollection
					final MetaDataCollection md = MetaDataCollection.createInstanceFromJson(jp);
					if (md == null)
						throw new IOException("Malformed medata at" + getPosition());

					if (!md.isEmpty()) {
						if (this.compatibleToOldCytoscapeAspect)
							migrateOldCyAspects(md);
						if (_encountered_non_meta_content) {
							_post_meta_data = md;
						} else {
							if (!preMetaDataReceived) {
								_pre_meta_data = md;
								preMetaDataReceived = true;
							}
						}
					}
					state = START;
				} else if (aspectName.equals(Status.NAME)) {
					if (_pre_meta_data == null && _post_meta_data == null)
						throw new IOException(
								"Status section appears before pre or post metadata found in the document.");

					_status = Status.createInstanceFromJson(jp);
					if (_status == null)
						throw new IOException("Malformed Status object at " + getPosition());
					if (_status.getError() != null && _status.getError().length() > 0)
						throw new IOException(
								"Error status received in CX document. Error message: " + _status.getError());
					state = END;
				} else if (_post_meta_data != null) {
					throw new IOException("Only " + Status.NAME
							+ " aspect is allowed after post metadata section. Error at " + getPosition());
				} else { // process a normal aspect fragment
					if (_post_meta_data != null)
						throw new IOException("New aspect fragement found after post metadata at " + getPosition());
					_reader = _element_readers.get(aspectName);
					if (_reader == null) {
						_reader = OpaqueFragmentReader.createInstance( aspectName);
					}
					_encountered_non_meta_content = true;
					state = ELEMENT_LIST_START;
				}
				/*
				 * } else if ( state == ASPECT_FRAGMENT_END) { if ( token !=
				 * JsonToken.END_ARRAY) throw new IOException ("End of array expected at: " +
				 * getPosition()); state = START;
				 */
			} else if (state == END) {
				if (token != JsonToken.END_ARRAY)
					throw new IOException("End of array expected at: " + getPosition());
				break;
			} else if (state == ELEMENT_LIST_START) {
				if (token != JsonToken.START_ARRAY)
					throw new IOException("Expect start of arry at: " + getPosition());
				state = IN_ELEMENT_LIST;
			} else if (state == IN_ELEMENT_LIST) {
				if (token == JsonToken.START_OBJECT) {
					final ObjectNode o = _m.readTree(jp);
					_current = _reader.readElement(o);
					if (_current == null)
						throw new IOException("Malformed object ecountered before " + getPosition());
					return true;
				} else if (token == JsonToken.END_ARRAY) {
					state = ELEMENT_LIST_END;
				}

			} else if (state == ELEMENT_LIST_END) {
				if (token != JsonToken.END_OBJECT)
					throw new IOException("End of Object expected at " + getPosition());
				state = START;
			}
		}
		return false;
	}

    /**
     * This returns a Iterator for AspectElements
     *
     * @return Iterator for AspectElements
     *
     */
    @Override
    public Iterator<AspectElement> iterator() {
        final Iterator<AspectElement> it = new Iterator<AspectElement>() {

            @Override
            public boolean hasNext() {
                try {
                    return CxElementReader2.this.hasNext();
                }
                catch (final IOException e) {
                	throw new RuntimeException("Error parsing element in CX stream: " + e.getMessage(),e);
                } 
            }

            @Override
            public AspectElement next() {
                    return CxElementReader2.this.getNext();  
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

 
    private void readNumberVerification () throws IOException {
       if ( jp.nextToken() != JsonToken.START_OBJECT) {
        	throw new IllegalStateException("Illegal cx format: expected to start with an Number Verification object.");
        }
        
        NumberVerification nv = null;
        nv = NumberVerification.createInstanceFromJson(jp);

        if ((nv != null)) {
            if ((!nv.getLongNumber().equals(CxConstants.LONG_NUMBER_TEST)) && (nv.getLongNumber().longValue() != Long.MAX_VALUE)) {
            	String errorMsg = "WARNING: number check is :" + nv.getLongNumber() + " but is expected to be " + CxConstants.LONG_NUMBER_TEST;
                throw new IOException(errorMsg);
            }
        }
    }

    /* Migrate the old metadata to new metadata entry, because the readers are converting 
     * them to new aspects.
     */
    
    private static void migrateOldCyAspects(MetaDataCollection metadata) {
      	migrateMetadata("visualProperties", "cyVisualProperties", metadata);
      	migrateMetadata("subNetworks", "cySubNetworks", metadata);
      	migrateMetadata("networkRelations", "cyNetworkRelations", metadata);
      	migrateMetadata("hiddenAttributes", "cyHiddenAttributes", metadata);
    	
    }
    
    private  static void migrateMetadata(String oldName, String newName, MetaDataCollection metadata) {
		MetaDataElement metaDataElmt = metadata.remove(oldName);
		if ( metaDataElmt != null ) {
			metaDataElmt.setName(newName);
			metadata.add(metaDataElmt);	
		}
    }
    
    public MetaDataCollection getPreMetaData() throws IOException {
    	hasNext();
    	return _pre_meta_data;
    }
    
    public MetaDataCollection getPostMetaData() {
    	return _post_meta_data;
    }
}
