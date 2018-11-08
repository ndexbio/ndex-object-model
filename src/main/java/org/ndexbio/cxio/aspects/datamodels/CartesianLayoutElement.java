package org.ndexbio.cxio.aspects.datamodels;

import java.io.IOException;

import org.ndexbio.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to represent the position of a network node in x, y, z coordinates.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CartesianLayoutElement extends AbstractAspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String ASPECT_NAME = "cartesianLayout";
    public final static String NODE        = "node";
    public final static String VIEW        = "view";
    public final static String X           = "x";
    public final static String Y           = "y";
    public final static String Z           = "z";
    
    @JsonProperty(NODE)
    private final Long         _node;
    
    @JsonProperty(VIEW)
    private final Long         _view;

    @JsonProperty(X)
    private final Double       _x;
    @JsonProperty(Y)
    private final Double       _y;
    @JsonProperty(Z)
    private final Double       _z;
    
    public CartesianLayoutElement(final Long node, final Long view, final String x, final String y) {
        _node = node;
        _view = view;
        _x = (x ==null) ? null : Double.valueOf(x);
        _y = (y ==null)? null : Double.valueOf(y);
        _z = null;
    }

    public CartesianLayoutElement(final Long node, final Long view, final String x, final String y, final String z) {
        _node = node;
        _view = view;
        _x = (x ==null) ? null : Double.valueOf(x);
        _y = (y ==null) ? null : Double.valueOf(y);
        _z = (z ==null)? null : Double.valueOf(z);
    }

    public CartesianLayoutElement(final Long node, final Long view, final double x, final double y, final double z) {
        _node = node;
        _view = view;
        _x = Double.valueOf(x);
        _y = Double.valueOf(y);
        _z = Double.valueOf(z);
    }



    public CartesianLayoutElement(final Long node, final String x, final String y) {
        _node = node;
        _view = null;
        _x = (x ==null) ? null : Double.valueOf(x);
        _y = (y ==null) ? null : Double.valueOf(y);
        _z = null;
    }

    public CartesianLayoutElement() {
        _node = null;
        _view = null;
        _x = null;
        _y = null;
        _z = null;
    }


    @Override
	@JsonIgnore
    public String getAspectName() {
        return CartesianLayoutElement.ASPECT_NAME;
    }

    final public Long getView() {
        return _view;
    }

    public Long getNode() {
        return _node;
    }
    
    public Double getX() {
        return _x;
    }

    public Double getY() {
        return _y;
    }

    public Double getZ() {
        return _z;
    }
    

  /*  public boolean isViewSet() {
        return _view_set;
    } */
    
    @JsonIgnore
    public boolean isZset() {return _z != null;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(ASPECT_NAME);
        sb.append(": ");
        sb.append("node: ");
        sb.append(_node);
        if (_view != null) {
            sb.append(", view: ");
            sb.append(_view);
        }
        sb.append(", x: ");
        sb.append(_x);
        sb.append(", y: ");
        sb.append(_y);
        if (_z != null) {
            sb.append(", z: ");
            sb.append(_z);
        }
        return sb.toString();
    }

	@Override
	public void write(JsonWriter w) throws IOException {
		
        w.writeStartObject();
        w.writeNumberField(CartesianLayoutElement.NODE, this.getNode());
        if (this._view != null) {
            w.writeNumberField(CartesianLayoutElement.VIEW, this.getView());
        }
        w.writeNumberField(CartesianLayoutElement.X, this.getX().doubleValue());
        w.writeNumberField(CartesianLayoutElement.Y, this.getY().doubleValue());
        if (_z != null) {
            w.writeNumberField(CartesianLayoutElement.Z, this.getZ().doubleValue());
        }
        w.writeEndObject();		
        w.flush();
	}

	
    /*
    public CartesianLayoutElement(final long node, final double x, final double y) {
        _node = node;
        _view = null;
        _x = String.valueOf(x);
        _y = String.valueOf(y);
        _z = String.valueOf(0);
        _z_set = false;
    //    _view_set = false;
    } 

    public CartesianLayoutElement(final long node, final Long view, final double x, final double y) {
        _node = node;
        _view = view;
        _x = String.valueOf(x);
        _y = String.valueOf(y);
        _z = String.valueOf(0);
        _z_set = false;
 //       _view_set = true;
    } */

	  /*  public CartesianLayoutElement(final long node, final double x, final double y, final double z) {
    _node = node;
    _view = null;
    _x = String.valueOf(x);
    _y = String.valueOf(y);
    _z = String.valueOf(z);
    _z_set = true;
//      _view_set = false;
} */
}
