package org.cxio.aspects.datamodels;

import java.io.IOException;

import org.cxio.util.JsonWriter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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
    private final long         _node;
    private final Long         _view;
    private final String       _x;
    private final String       _y;
    private final String       _z;
    private final boolean      _z_set;
    //private final boolean      _view_set;

    public CartesianLayoutElement(final long node, final Long view, final String x, final String y) {
        _node = node;
        _view = view;
        _x = x;
        _y = y;
        _z = String.valueOf(0);
        _z_set = false;
   //     _view_set = true;
    }

    public CartesianLayoutElement(final long node, final Long view, final String x, final String y, final String z) {
        _node = node;
        _view = view;
        _x = x;
        _y = y;
        _z = z;
        _z_set = true;
  //      _view_set = true;
    }

    public CartesianLayoutElement(final long node, final Long view, final double x, final double y, final double z) {
        _node = node;
        _view = view;
        _x = String.valueOf(x);
        _y = String.valueOf(y);
        _z = String.valueOf(z);
        _z_set = true;
  //      _view_set = true;
    }



    public CartesianLayoutElement(final long node, final String x, final String y) {
        _node = node;
        _view = null;
        _x = x;
        _y = y;
        _z = String.valueOf(0);
        _z_set = false;
 //       _view_set = false;
    }

 /*   public CartesianLayoutElement(final Long node, final String x, final String y, final String z) {
        _node = node;
        _view = null;
        _x = x;
        _y = y;
        _z = z;
        _z_set = true;
   //     _view_set = false;
    } */

    @Override
    public String getAspectName() {
        return CartesianLayoutElement.ASPECT_NAME;
    }

    final public Long getView() {
        return _view;
    }

    public long getNode() {
        return _node;
    }

    public String getX() {
        return _x;
    }

    public String getY() {
        return _y;
    }

    public String getZ() {
        return _z;
    }

    public boolean isZset() {
        return _z_set;
    }

  /*  public boolean isViewSet() {
        return _view_set;
    } */

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
        if (_z_set) {
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
        w.writeNumberField(CartesianLayoutElement.X, Double.parseDouble(this.getX()));
        w.writeNumberField(CartesianLayoutElement.Y, Double.parseDouble(this.getY()));
        if (this.isZset()) {
            w.writeNumberField(CartesianLayoutElement.Z, Double.parseDouble(this.getZ()));
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
