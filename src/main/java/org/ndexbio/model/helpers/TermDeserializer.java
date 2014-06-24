package org.ndexbio.model.helpers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.ndexbio.model.object.network.BaseTerm;
import org.ndexbio.model.object.network.FunctionTerm;
import org.ndexbio.model.object.network.ReifiedEdgeTerm;
import org.ndexbio.model.object.network.Term;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TermDeserializer extends JsonDeserializer<Term>
{
    public TermDeserializer()
    {
        super();
    }
    
    
   
    @Override
    public Term deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException
    {
   /*     final ObjectMapper jsonMapper = new ObjectMapper();
        final JsonNode serializedTerm = jsonMapper.readTree(jsonParser);
        final JsonNode termType = serializedTerm.get("termType");
        
        if (termType != null)
        {
            if (termType.asText().equals("Base"))
                return populateBaseTerm(serializedTerm);
            else if (termType.asText().equals("Function"))
                return populateFunctionTerm(serializedTerm);
            else if (termType.asText().equals("ReifiedEdge"))
                return populateReifiedEdgeTerm(serializedTerm);
        }
        else
        {
            final JsonNode nameProperty = serializedTerm.get("name");
            if (nameProperty != null)
                return populateBaseTerm(serializedTerm);
            
            final JsonNode functionProperty = serializedTerm.get("termFunction");
            if (functionProperty != null)
                return populateFunctionTerm(serializedTerm);
            
            final JsonNode reifiedEdgeProperty = serializedTerm.get("termEdge");
            if (reifiedEdgeProperty != null)
                return populateReifiedEdgeTerm(serializedTerm);
        }
     */   
        throw context.mappingException("Unsupported term type.");
    }
    
    
    
  /*  private ReifiedTerm populateReifiedEdgeTerm(JsonNode serializedTerm) {
        final ReifiedEdgeTerm reifiedEdgeTerm = new ReifiedEdgeTerm();
        
        reifiedEdgeTerm.setTermEdge(serializedTerm.get("termEdge").asLong);
                
        return reifiedEdgeTerm;
	}



	private BaseTerm populateBaseTerm(JsonNode serializedTerm)
    {
        final BaseTerm baseTerm = new BaseTerm();
        baseTerm.setName(serializedTerm.get("name").asText());
        
        if (serializedTerm.get("namespace") != null)
        {        
            baseTerm.setNamespace(serializedTerm.get("namespace").asLong());
        }
        
        return baseTerm;
    }
    
    private FunctionTerm populateFunctionTerm(JsonNode serializedTerm)
    {
        final FunctionTerm functionTerm = new FunctionTerm();
        functionTerm.setTermFunction((BaseTerm)(serializedTerm.get("termFunction")));
        final List<Long> parameters = functionTerm.getParameters();
        Iterator<Entry<String, JsonNode>> fieldIterator = serializedTerm.get("parameters").fields();

        while (fieldIterator.hasNext()){
        	Entry<String, JsonNode> param = fieldIterator.next();
        	parameters.put(param.getKey(), param.getValue().asText());
        }
        functionTerm.setParameters(parameters);
        
        return functionTerm;
    }
 */
}
