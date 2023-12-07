package org.ndexbio.cx2.aspect.element.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EdgeLabelPositionTest {

		   @Test
		    public void testCreateFromCX1Value() {
		        String cx1Value = "SE,NW,l,0.00,27.00";
		        EdgeLabelPosition edgeLabelPosition = EdgeLabelPosition.createFromCX1Value(cx1Value);

		        assertEquals(HorizontalAlignment.left, edgeLabelPosition.getJustification(), "Justification should be LEFT");
		        assertEquals("SE", edgeLabelPosition.getEdgeAnchorPoints(), "Edge anchor points should be 'SE'");
		        assertEquals("NW", edgeLabelPosition.getLabelAnchorPoints(), "Label anchor points should be 'NW'");
		        assertEquals(0.00f, edgeLabelPosition.getMarginX(), "Margin X should be 0.00");
		        assertEquals(27.00f, edgeLabelPosition.getMarginY(), "Margin Y should be 27.00");
		    }

		    @Test
		    public void testToCX1String() {
		        EdgeLabelPosition edgeLabelPosition = new EdgeLabelPosition();
		        edgeLabelPosition.setJustification(HorizontalAlignment.center);
		        edgeLabelPosition.setEdgeAnchorPoints("C");
		        edgeLabelPosition.setLabelAnchorPoints("C");
		        edgeLabelPosition.setMarginX(1.5f);
		        edgeLabelPosition.setMarginY(-2.5f);

		        String expectedCX1String = "C,C,c,1.5,-2.5";
		        assertEquals(expectedCX1String, edgeLabelPosition.toCX1String(), "CX1 string representation should match");
		    }


}
