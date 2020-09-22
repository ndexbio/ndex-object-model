package org.ndexbio.cx2.converter;

import java.util.List;

/**
 * Pojo class to hold result from 
 * {@link org.ndexbio.cx2.converter.ConverterUtilities#cvtStringValueToObj}
 * method
 * 
 * @author churas
 */
public class ConverterUtilitiesResult {
    
    private List<String> _warnings;
    private Object _result;
    
    /**
     * Constructor
     * @param result The result 
     * @param warnings Any warnings generated from result
     */
    public ConverterUtilitiesResult(final Object result, List<String> warnings){
        _warnings = warnings;
        _result = result;
    }
    
    /**
     * Gets warnings passed in via constructor
     * @return Any warnings or {@code null} if none passed in
     */
    public List<String> getWarnings(){
        return _warnings;
    }
    
    /**
     * Denotes whether any warnings were passed in via the constructor
     * 
     * @return {@code TRUE} if the warnings passed in via constructor
     *         is not {@code null} and has at least one entry otherwise
     *         {@code FALSE}
     */
    public boolean hasWarnings(){
        if (_warnings == null || _warnings.isEmpty()){
            return false;
        }
        return true;
    }
    
    /**
     * Gets result
     * @return The result passed in via constructor
     */
    public Object getResult(){
        return _result;
    }
}
