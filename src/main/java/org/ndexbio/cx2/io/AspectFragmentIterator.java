package org.ndexbio.cx2.io;

import java.util.Iterator;
import java.util.Map;

import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxNode;
import org.ndexbio.cxio.misc.OpaqueElement;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AspectFragmentIterator implements Iterator<FragmentElementIterator>{

	private JsonParser jp;
	private ObjectMapper om;
	private String aspectName;
	
    private  Map<String, Class<?>> aspectTable;

	
	public AspectFragmentIterator( JsonParser jp, ObjectMapper om, String aspectName) {
		//vType = valueType;
		this.jp = jp;
		this.om = om;
		this.aspectName = aspectName;
		
		aspectTable.put("nodes", CxNode.class);
	    aspectTable.put("edges", CxEdge.class);

	}
	
	@Override
	public boolean hasNext() {
		if ( aspectName != null)
			return true;
		return false;
	}

	@Override
	public FragmentElementIterator<?> next() {
		Class<?> t = aspectTable.get(aspectName);
		if (t == null)
			t = OpaqueElement.class;
		FragmentElementIterator<?> i = new FragmentElementIterator( t, jp);
		return i;
	}
	

	public String getAspectName () {
		return this.aspectName;
	}
}
