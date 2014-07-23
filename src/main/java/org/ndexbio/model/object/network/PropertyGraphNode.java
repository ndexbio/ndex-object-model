package org.ndexbio.model.object.network;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PropertyGraphNode extends PropertiedNetworkElement implements Comparable <PropertyGraphNode> {

   public static final String represents = "NDEX:represents";
   public static final String aliases    = "NDEX:aliases";
   public static final String relatedTerms   = "NDEX:relatedTerms";
   public static final String name       = "NDEX:nodeName";
	
   public PropertyGraphNode () {
      super();
   }


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

}
