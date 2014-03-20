package org.ndexbio.model.object;

public class MetaParameter
{
    private String _key;
    private char _operator;
    private String _keywords;
    
    
    
    /**************************************************************************
    * Default constructor - initializes the created date. 
    **************************************************************************/
    public MetaParameter()
    {
        
    }
    
    /**************************************************************************
    * Initializes the class and populates the properties. 
    **************************************************************************/
    public MetaParameter(String key, char operator, String keywords)
    {
        _key = key;
        _operator = operator;
        _keywords = keywords;
    }
    
    
    
    public String getKey()
    {
        return _key;
    }
    
    public void setKey(String key)
    {
        _key = key;
    }
    
    public char getOperator()
    {
        return _operator;
    }
    
    public void setOperator(char operator)
    {
        _operator = operator;
    }
    
    public String getKeywords()
    {
        return _keywords;
    }
    
    public void setKeywords(String keywords)
    {
        _keywords = keywords;
    }



    /**************************************************************************
    * Gets the SQL for the operator and keywords.
    *  
    * @return A string containing SQL for the operator.
    **************************************************************************/
    @Override
    public String toString()
    {
        switch (_operator)
        {
            case '=':
                return " = '" + _keywords + "'";
            case ':':
                return " LIKE '" + _keywords + "%'";
            case '~':
                return " LIKE '%" + _keywords + "%'";
            default:
                throw new IllegalArgumentException ("Unsupported operator encountered: " + _operator);
        }
    }
}
