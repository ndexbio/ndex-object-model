package org.cxio.aspects.datamodels;

import org.cxio.util.CxioUtil;

public final class Mapping {

    public final static String TYPE       = "type";
    public final static String DEFINITION = "definition";

    private  String       _type;
    private  String       _definition;

    public Mapping () {}
    
    public Mapping(final String type, final String definition) {
        if (CxioUtil.isEmpty(type)) {
            throw new IllegalArgumentException("mappping type must not be null or empty");
        }
        if (CxioUtil.isEmpty(definition)) {
            throw new IllegalArgumentException("mappping definition must not be null or empty");
        }
        _type = type;
        _definition = definition;
    }

    public final String getType() {
        return _type;
    }
    
    public void setType (String type) { _type = type;}

    public final String getDefinition() {
        return _definition;
    }
    
    public void setDefinition (String definition) { _definition = definition;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(_type);
        sb.append(": ");
        sb.append(_definition);
        return sb.toString();
    }
}
