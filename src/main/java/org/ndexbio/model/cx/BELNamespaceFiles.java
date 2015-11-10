package org.ndexbio.model.cx;

import java.util.Map;
import java.util.TreeMap;

import org.cxio.aspects.datamodels.AbstractAspectElement;

public class BELNamespaceFiles extends AbstractAspectElement {

	final public static String ASPECT_NAME           = "BELNamespaceFiles";
	
	private Map<String,String> namespaceFileTable;

	public BELNamespaceFiles() {
		setNamespaceFileTable(new TreeMap<String,String>());
	}
	
	

	@Override
	public String getAspectName() {
		return ASPECT_NAME;
	}



	public Map<String,String> getNamespaceFileTable() {
		return namespaceFileTable;
	}



	public void setNamespaceFileTable(Map<String,String> namespaceFileTable) {
		this.namespaceFileTable = namespaceFileTable;
	}

}
