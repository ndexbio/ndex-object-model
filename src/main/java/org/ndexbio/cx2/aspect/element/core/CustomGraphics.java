package org.ndexbio.cx2.aspect.element.core;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)

public class CustomGraphics implements ComplexVPValue {

	//prefix of the image type custom graphics in the full name.
	private static final String imagePrefix = "org.cytoscape.ding.customgraphics.";
	
	//these are predefined attributes for image type custom graphics.
	private static final String ID = "id";
	private static final String URL = "url";
	private static final String TAG = "tag";
	
	private static final String NAME = "name";
	private static final String TYPE = "type";
	private static final String PROPERTIES = "properties";
	
	//type of the custom graphics. Can be image or chart
	@JsonProperty(TYPE)
	private String type;
	
	// fully qualified name of the custom graphics.
	@JsonProperty(NAME)
	private String fullName;
	
	// properties of the custom graphics.
	@JsonProperty(PROPERTIES)
    @JsonDeserialize(using = CustomPropertiesDeserializer.class)
	private Map<String,Object> properties;
	
	@Override
	public String toCX1String() {
		if (type.equals("image")) {
			return fullName + "," + properties.get(ID) + "," + 
		            properties.get(URL) + "," + properties.get(TAG);
		} else {
			ObjectMapper mapper = new ObjectMapper();
			try {
				return fullName + ":" + mapper.writeValueAsString(properties);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Map<String,Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String,Object> properties) {
		this.properties = properties;
	}
	
	public static CustomGraphics createFromCX1Value(String cx1Value) throws NdexException {
		CustomGraphics result = new CustomGraphics();
		if ( cx1Value.startsWith(imagePrefix))  {
			String[] values = cx1Value.split(",");

			result.setFullName(values[0]);
			result.setType("image");
			result.setProperties(Map.of(ID, Long.valueOf(values[1]), URL, values[2], TAG, values[3]));
			return result;
		} else {
			 int colonIndex = cx1Value.indexOf(':');
		     if (colonIndex == -1) {
		        throw new NdexException("Invalid CX1 string value for custom graphics: " + cx1Value);
		     }

		     result.setFullName (cx1Value.substring(0, colonIndex));
		     result.setType("chart");
		     ObjectMapper objectMapper = new ObjectMapper();
             String json = cx1Value.substring(colonIndex + 1);
             try {
				result.setProperties( objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {}));
             } catch (JsonProcessingException e) {
				throw new NdexException (e.getMessage());
             }
             return result;    
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof CustomGraphics))
			return false;
		CustomGraphics other = (CustomGraphics) o;
		return this.type.equals(other.type) && this.fullName.equals(other.fullName)
				&& this.properties.equals(other.properties);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(type, fullName, properties);
	}
	
	
	 private static class CustomPropertiesDeserializer extends JsonDeserializer<Map<String, Object>> {

	        @Override
	        public Map<String, Object> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
	            ObjectMapper mapper = new ObjectMapper();
	            Map<String, Object> properties = mapper.readValue(p, new TypeReference<Map<String, Object>>() {});

	            properties.computeIfPresent(ID, (key, value) -> {
                    if (value instanceof Integer) {
                        return ((Integer) value).longValue();
                    }
                    return value;
                });

	            return properties;
	        }
	    }
	
	public static CustomGraphics createFromMap(Map<String,Object> m){
		
		CustomGraphics result = new CustomGraphics();
		
		result.setType((String)m.get(TYPE));
		result.setFullName(m.get(NAME).toString());
		
		result.setProperties((Map<String,Object>)m.get(PROPERTIES));

		return result;
		
	} 

}
