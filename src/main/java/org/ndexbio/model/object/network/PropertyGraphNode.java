/**
 * Copyright (c) 2013, 2015, The Regents of the University of California, The Cytoscape Consortium
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.ndexbio.model.object.network;


import org.ndexbio.model.object.NdexPropertyValuePair;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyGraphNode extends PropertiedNetworkElement implements Comparable <PropertyGraphNode> {

   public static final String represents = "NDEX:represents";
   public static final String aliases    = "NDEX:aliases";
   public static final String relatedTerms   = "NDEX:relatedTerms";
   public static final String name       = "dc:title";
	
   public PropertyGraphNode () {
      super();
   }


@Override
public int compareTo(PropertyGraphNode o) {
	long c = this.getId() - o.getId();
	
	if ( c > 0) return 1;
	if ( c < 0) return -1;
	return 0;
	
}
   
@Override
public int hashCode () { return (int) getId(); }

 @Override
public boolean equals(Object anObject) {
	 if ( anObject instanceof PropertyGraphNode) {
		return ((PropertyGraphNode)anObject).getId() == getId();
	 }
	 return false;
 }

 	/**
 	 * Set the name of the node.
 	 * @param name
 	 */
 	public void setName (String nodeName) {
	  for ( NdexPropertyValuePair p : this.getProperties()) {
		  if ( p.getPredicateString().equals(name)) {
			  p.setValue(nodeName);
			  return;
		  }   
	  }
	  NdexPropertyValuePair p = new NdexPropertyValuePair(name, nodeName);
	  this.getProperties().add(p);

 	}

 	
 	  public String getName () {
 		  for ( NdexPropertyValuePair p : this.getProperties()) {
 			  if ( p.getPredicateString().equals(name))
 				  return p.getValue();
 		  }
 		  return null;
 	  }

}
