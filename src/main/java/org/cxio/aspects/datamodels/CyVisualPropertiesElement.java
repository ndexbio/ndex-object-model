package org.cxio.aspects.datamodels;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is used to represent a visual property of a
 * network, node(s), or edge(s) in/under a network view.
 *
 * @author cmzmasek
 *
 */
public final class CyVisualPropertiesElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4510933825037421074L;
	public final static String               APPLIES_TO    = "applies_to";
    public final static String               ASPECT_NAME   = "cyVisualProperties"; //"visualProperties" is the old aspectName before 1.1 fix.;
    public final static String               VIEW          = "view";
    public final static String               PROPERTIES    = "properties";
    public final static String               MAPPINGS      = "mappings";
    public final static String               DEPENDENCIES  = "dependencies";
    public final static String               PROPERTIES_OF = "properties_of";

    private Long                 _applies_to;
    private Long                               _view;
    private  SortedMap<String, String>  _properties;
    private  SortedMap<String, String>  _dependencies;
    private  SortedMap<String, Mapping> _mappings;
    private  String                     _properties_of;

    public CyVisualPropertiesElement () {
    	super();
    	
    	_properties_of = null;
    	_applies_to = null;
    	_view = null;
    	_properties = new TreeMap<> ();
    	_dependencies = new TreeMap<>();
    	_mappings = new TreeMap<> ();
    	
    }
    
    public CyVisualPropertiesElement(final String properties_of) {
        _properties_of = properties_of;
        _applies_to = null;
        _properties = new TreeMap<>();
        _dependencies = new TreeMap<>();
        _mappings = new TreeMap<>();
        _view = null;
    }

 /*   public CyVisualPropertiesElement(final String properties_of, final long view) {
        _properties_of = properties_of;
        _applies_to = null;
        _properties = new TreeMap<String, String>();
        _dependencies = new TreeMap<String, String>();
        _mappings = new TreeMap<String, Mapping>();
        _view = view;
    }

    public CyVisualPropertiesElement(final String properties_of, final Long applies_to) {
        _properties_of = properties_of;
        _applies_to = applies_to;
        _properties = new TreeMap<String, String>();
        _dependencies = new TreeMap<String, String>();
        _mappings = new TreeMap<String, Mapping>();
        _view = null;
    }*/ 

    public CyVisualPropertiesElement(final String properties_of, final Long applies_to, final Long view) {
        _properties_of = properties_of;
        _applies_to = applies_to;
        _properties = new TreeMap<>();
        _dependencies = new TreeMap<>();
        _mappings = new TreeMap<>();
        _view = view;
    }

 /*   public final void addAppliesTo(final String applies_to) {
        _applies_to.add(Long.valueOf(applies_to));
    }

    public final void addAppliesTo(final long applies_to) {
        _applies_to.add(applies_to);
    } */

    public final Long getApplies_to() {
        return _applies_to;
    }
    
    public void setApplies_to (Long appTo) { _applies_to = appTo; }

    @Override
    @JsonIgnore
    public String getAspectName() {
        return ASPECT_NAME;
    }

    final public Long getView() {
        return _view;
    }
    
    public void setView (Long v) { _view = v;}

    public final SortedMap<String, String> getProperties() {
        return _properties;
    }

    public void setProperties (SortedMap<String,String> p) { _properties = p; }
    
    public final SortedMap<String, String> getDependencies() {
        return _dependencies;
    }

    public void setDependencies ( SortedMap<String,String> d) {_dependencies = d; }
    
    public final SortedMap<String, Mapping> getMappings() {
        return _mappings;
    }

    public void setMappings ( SortedMap<String, Mapping> m ) { _mappings = m; }
    
    public final String getProperties_of() {
        return _properties_of;
    }
    
    public void setProperties_of (String po) { _properties_of = po; }

    public final void putProperty(final String name, final String value) {
        _properties.put(name, value);
    }

    public final void putDependency(final String name, final String value) {
        _dependencies.put(name, value);
    }

    public final void putMapping(final String name, final String type, final String definition) {
        _mappings.put(name, new Mapping(type, definition));
    }

    public final void putMapping(final String name, final Mapping mapping) {
        _mappings.put(name, mapping);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(ASPECT_NAME);
        sb.append(": ");
        sb.append("properties of: ");
        sb.append(_properties_of);
        sb.append("\n");
        if (_view != null) {
            sb.append("view: ");
            sb.append(_view);
            sb.append("\n");
        }
        sb.append("applies to: " + _applies_to );
        
        sb.append("\n");
        sb.append("properties:");
        sb.append("\n");
        for (final Map.Entry<String, String> entry : _properties.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("dependencies:");
        sb.append("\n");
        for (final Map.Entry<String, String> entry : _dependencies.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("mappings:");
        sb.append("\n");
        for (final Entry<String, Mapping> entry : _mappings.entrySet()) {
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue().toString());
            sb.append("\n");
        }
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
        final CyVisualPropertiesElement c = this;
        w.writeStartObject();
        w.writeStringField(CyVisualPropertiesElement.PROPERTIES_OF, c.getProperties_of());
        w.writeNumberFieldIfNotEmpty(CyVisualPropertiesElement.APPLIES_TO, c.getApplies_to());
        
        w.writeNumberFieldIfNotEmpty(CyVisualPropertiesElement.VIEW, c.getView());
        if ((c.getProperties() != null) && !c.getProperties().isEmpty()) {
            w.writeObjectFieldStart(CyVisualPropertiesElement.PROPERTIES);
            for (final Map.Entry<String, String> entry : c.getProperties().entrySet()) {
                if (entry.getValue() != null) {
                    w.writeStringField(entry.getKey(), entry.getValue());
                }
            }
            w.writeEndObject();
        }
        if ((c.getDependencies() != null) && !c.getDependencies().isEmpty()) {
            w.writeObjectFieldStart(CyVisualPropertiesElement.DEPENDENCIES);
            for (final Map.Entry<String, String> entry : c.getDependencies().entrySet()) {
                if (entry.getValue() != null) {
                    w.writeStringField(entry.getKey(), entry.getValue());
                }
            }
            w.writeEndObject();
        }
        if ((c.getMappings() != null) && !c.getMappings().isEmpty()) {
            w.writeObjectFieldStart(CyVisualPropertiesElement.MAPPINGS);
            for (final Entry<String, Mapping> entry : c.getMappings().entrySet()) {
                if (entry.getValue() != null) {
                    w.writeObjectFieldStart(entry.getKey());
                    final Mapping m = entry.getValue();
                    w.writeStringField(Mapping.TYPE, m.getType());
                    w.writeStringField(Mapping.DEFINITION, m.getDefintion());
                    w.writeEndObject();
                }
            }
            w.writeEndObject();
        }
        w.writeEndObject();
		w.flush();
	}

}
