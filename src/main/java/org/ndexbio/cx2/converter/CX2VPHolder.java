package org.ndexbio.cx2.converter;

import java.util.ArrayList;
import java.util.List;

import org.ndexbio.cx2.aspect.element.core.CxEdgeBypass;
import org.ndexbio.cx2.aspect.element.core.CxNodeBypass;
import org.ndexbio.cx2.aspect.element.core.CxVisualProperty;

public class CX2VPHolder {
	
	
	private CxVisualProperty style; 
	
	private List<CxNodeBypass> nodeBypasses; 
	private List<CxEdgeBypass> edgeBypasses;   

	public CX2VPHolder () {
		setStyle(new CxVisualProperty());
		
		setNodeBypasses(new ArrayList<>());
		setEdgeBypasses(new ArrayList<>());  
	}

	public CxVisualProperty getStyle() {
		return style;
	}

	public void setStyle(CxVisualProperty style) {
		this.style = style;
	}

	public List<CxNodeBypass> getNodeBypasses() {
		return nodeBypasses;
	}

	public void setNodeBypasses(List<CxNodeBypass> nodeBypasses) {
		this.nodeBypasses = nodeBypasses;
	}

	public List<CxEdgeBypass> getEdgeBypasses() {
		return edgeBypasses;
	}

	public void setEdgeBypasses(List<CxEdgeBypass> edgeBypasses) {
		this.edgeBypasses = edgeBypasses;
	}
}
