package org.ndexbio.cx2.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.ndexbio.cx2.aspect.element.core.CxAttributeDeclaration;
import org.ndexbio.cx2.aspect.element.core.CxAspectElement;
import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cx2.aspect.element.core.CxMetadata;
import org.ndexbio.cx2.aspect.element.core.CxOpaqueAspectElement;
import org.ndexbio.cxio.misc.Status;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CXReader implements Iterable<CxAspectElement> {
	
	private final static int START = 0;
	private final static int AFTER_HEADER = 1;
	private final static int ASPECT_FRAGMENT = 3;
	private final static int END = 4;
	private final static int ELEMENT_LIST_START = 5;
	private final static int IN_ELEMENT_LIST = 6;
	private final static int ELEMENT_LIST_END = 7;
	
	public final static String metadataAspectName = "metaData";
	
	private Map<String, CxMetadata> metadataTable;
    private CxAspectElement        _current;
    private int metadataAspectCount ;

	private CxAttributeDeclaration attrDeclarations;
    Status              _status;

    private JsonParser   jp;
    private ObjectMapper  om;
    private int state;
    private String currentAspect; 
    private Class<? extends CxAspectElement> currentAspectClass;
    
    private  Map<String, Class<? extends CxAspectElement>> aspectTable;

    
	public CXReader (InputStream input) throws JsonParseException, IOException {
		final JsonFactory f = new JsonFactory();
        jp = f.createParser(input);
        om =new ObjectMapper();
        
        metadataTable = new HashMap<>();
        attrDeclarations = null;
        
        state = START;
        _current = null;
        metadataAspectCount = 0;
        currentAspectClass = null;
        
        aspectTable = new HashMap<>();
        
    	aspectTable.put("nodes", CxNode.class);
	    aspectTable.put("edges", CxEdge.class);
	    aspectTable.put("attributeDeclarations", CxAttributeDeclaration.class);
	    
	}
	
	
	private void verifyHeader() throws IOException {
		JsonToken token = jp.nextToken();
		if (token != JsonToken.START_OBJECT) {
		   throw parsingError("Expect '{'");
			
		}		
		TypeReference<HashMap<String,Object>> typeRef 
        = new TypeReference<HashMap<String,Object>>() {};
        
        
		Map<String, Object> r = om.readValue(jp, typeRef);
		
		if ( r.get("CXVersion") == null)
			throw new  IllegalStateException("illegal cx format: expected to CX version object in the first element, but has: " + jp.getCurrentToken().asString());
			
	}
	
	
	
	public AspectFragmentIterator getAspectFragmentIterator () throws IOException {
		JsonToken token = jp.nextToken();
		if (token != JsonToken.START_ARRAY) {
	       throw parsingError("Expect '['");
	    }
	    	  	 
		
		token = jp.nextToken();
		if (token != JsonToken.START_OBJECT) {
		   throw parsingError("Expect '{'");
			
		}		
		TypeReference<HashMap<String,Object>> typeRef 
        = new TypeReference<HashMap<String,Object>>() {};
        
        
		Map<String, Object> r = om.readValue(jp, typeRef);
		
		if ( r.get("CXVersion") == null)
			throw new  IllegalStateException("illegal cx format: expected to CX version object in the first element, but has: " + jp.getCurrentToken().asString());
			
		
		//check if metadata exists 
		
		token = jp.nextToken();
		if (token != JsonToken.START_OBJECT) {
		   throw parsingError ("Expect '{'"); 	
		}
		
		token = jp.nextToken();
		if ( token != JsonToken.FIELD_NAME) {
			throw parsingError ("Expect field name");
		}
		
		String aspectName = jp.getCurrentName();

		if ( aspectName.equals(metadataAspectName)) {
			processMetadata();
			token = jp.nextToken();
			if ( token != JsonToken.START_OBJECT)
				throw parsingError("Expect '{'");
			token = jp.nextToken();
			if ( token != JsonToken.FIELD_NAME)
				throw new IllegalStateException("Expect field name at:" + jp.getCurrentLocation());
			aspectName = jp.getCurrentName();
		} 
		

		
		while (jp.nextToken() != null) {
			token = jp.currentToken();
		}	
		return null;
	}
	
	private void processMetadata() throws IOException {
		JsonToken token = jp.nextToken();
		if ( token != JsonToken.START_ARRAY)
			throw parsingError("Expect '['");
		while (jp.nextToken()!=null && jp.nextToken() != JsonToken.END_ARRAY) {
			token = jp.currentToken();
			if(token == JsonToken.END_OBJECT)
				break;
			CxMetadata e = om.readValue(jp, CxMetadata.class);
			metadataTable.put(e.getName(), e);
		}
		metadataAspectCount ++;
	}


	@Override
	public Iterator<CxAspectElement> iterator() {
		 final Iterator<CxAspectElement> it = new Iterator<CxAspectElement>() {

	            @Override
	            public boolean hasNext() {
	                try {
	                    return CXReader.this.hasNext();
	                }
	                catch (final IOException e) {
	                	throw new RuntimeException("Error parsing element in CX stream: " + e.getMessage(),e);
	                } 
	            }

	            @Override
	            public CxAspectElement next() {
	                    return CXReader.this.getNext();  
	            }

	            @Override
	            public void remove() {
	                throw new UnsupportedOperationException();
	            }
	        };
	        return it;
	}
	
    private final CxAspectElement getNext() {
    	if ( _current != null) {
    		CxAspectElement result = _current;
    		_current = null;
    		return result;
    	}
    	
    	throw new NoSuchElementException(); 
    }
	
	
	private final boolean hasNext() throws IOException {
		if (_current != null)
			return true;
		while (jp.nextToken() != null) {
			JsonToken token = jp.getCurrentToken();
			if (state == ASPECT_FRAGMENT) {
				if (token != JsonToken.FIELD_NAME)
					throw new IOException("Expecting aspect name as a field name at " + getPosition());

				currentAspect = jp.getCurrentName();
				if (currentAspect.equals(metadataAspectName)) { // MetaDataCollection
					if ( metadataAspectCount == 2)
						throw parsingError("Only 2 metadata aspects are allowed in a CX network ");
					processMetadata();
					state = AFTER_HEADER;
				} else if (currentAspect.equals(Status.NAME)) {
					_status =  om.readValue(jp, Status.class);
				    if ( _status.getStatus().size() != 1)
				        	throw new IOException ("Aspect 'status' should have one element in it.");
					if (_status == null)
						throw new IOException("Malformed Status object at " + getPosition());
					if (_status.getError() != null && _status.getError().length() > 0)
						throw new IOException(
								"Error status received in CX document. Error message: " + _status.getError());
					state = END;
				} else if (metadataAspectCount ==2 ) {
					throw new IOException("Only " + Status.NAME
							+ " aspect is allowed after post metadata section. Error at " + getPosition());
				} else { // process a normal aspect fragment
//					if (_post_meta_data != null)
//						throw new IOException("New aspect fragement found after post metadata at " + getPosition());
					currentAspectClass = aspectTable.get(currentAspect);
					/*if (currentAspectClass == null) {
						_reader = OpaqueFragmentReader.createInstance( aspectName);
					} */
					state = ELEMENT_LIST_START;
				}
				/*
				 * } else if ( state == ASPECT_FRAGMENT_END) { if ( token !=
				 * JsonToken.END_ARRAY) throw new IOException ("End of array expected at: " +
				 * getPosition()); state = START;
				 */
			} /*else if ( state == BEFORE_PRE_METADATA) {
				if (token != JsonToken.FIELD_NAME)
					throw new IOException("Expecting aspect name as a field name at " + getPosition());

				String aspectName = jp.getCurrentName();
				if (aspectName.equals(MetaDataCollection.NAME)) { // MetaDataCollection
					

				} else if (aspectName.equals(Status.NAME)) {
					while (jp.nextToken()!=null && jp.nextToken() != JsonToken.END_ARRAY) {
						token = jp.currentToken();
						if(token == JsonToken.END_OBJECT)
							break;
						Metadata e = om.readValue(jp, Metadata.class);
						metadataTable.put(e.getName(), e);
					}
				} else 
					throw new IOException ("MetaData is expected at the beginning of a CX document.");
						    	
		    }*/ else if (state == AFTER_HEADER)  { 
		    	if (token != JsonToken.START_OBJECT) {
					throw parsingError("Expecting '{'");
				}
		    	state = ASPECT_FRAGMENT;
		    } else if (state == ELEMENT_LIST_START) {
				if (token != JsonToken.START_ARRAY)
					throw new IOException("Expect start of arry at: " + getPosition());
				state = IN_ELEMENT_LIST;
			} else if (state == IN_ELEMENT_LIST) {
				if (token == JsonToken.START_OBJECT) {
					if ( this.currentAspectClass !=null)
					   _current = om.readValue(jp, this.currentAspectClass);
					else { 
					   _current = om.readValue(jp,CxOpaqueAspectElement.class);
					   ((CxOpaqueAspectElement)_current).setAspectName(this.currentAspect);
					}		
					if (_current == null)
						throw new IOException("Malformed object ecountered before " + getPosition());
					return true;
				} else if (token == JsonToken.END_ARRAY) {
					state = ELEMENT_LIST_END;
				}
			} else if (state == ELEMENT_LIST_END) {
				if (token != JsonToken.END_OBJECT)
					throw new IOException("End of Object expected at " + getPosition());
				state = AFTER_HEADER;
			} else if (state == START) {
				if (token == JsonToken.END_ARRAY) { // End of Aspect fragment list.
					if (_status == null)
						throw parsingError("CX document ends without a Status object defined");
					break;
				}
				
				verifyHeader();
				state = AFTER_HEADER;
			} else if (state == END) {
				if (token != JsonToken.END_ARRAY)
					throw new IOException("End of array expected at: " + getPosition());
				break;
			}
		}
		return false;
	}

	
	private IllegalStateException parsingError ( String errorMessage) {
		return new IllegalStateException(errorMessage + getPosition());
	}
	
	private String getPosition() {
	    	return " at line: " + jp.getCurrentLocation().getLineNr() + ", column: "
					+ jp.getCurrentLocation().getColumnNr();
	}
	
}
