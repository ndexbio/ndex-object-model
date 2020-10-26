package org.ndexbio.cx2.io;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

import org.ndexbio.cx2.aspect.element.core.CxEdge;
import org.ndexbio.cx2.aspect.element.core.CxNode;

import com.fasterxml.jackson.core.JsonParser;

public class FragmentElementIterator<T> implements Iterator<T> {
	
	
 Map<String, Class> aspectTable;

	private Class<T> vType;
	private JsonParser jp;
	
	public FragmentElementIterator (Class<T> valueType, JsonParser jp) {
		this.vType = valueType;
		this.jp = jp;
		

	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}


}
