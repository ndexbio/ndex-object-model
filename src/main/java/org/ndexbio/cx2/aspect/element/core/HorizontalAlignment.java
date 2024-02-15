package org.ndexbio.cx2.aspect.element.core;

public enum HorizontalAlignment {
  left,center,right;
	
	 public String toCX1() {
	        switch (this) {
	            case center:
	                return "c";
	            case left:
	                return "l";
	            default:
	                return "r";
	        }
	    }
	 
	  public static HorizontalAlignment fromCX1(String c) {
		  
		  switch ( c ) {
			case "c":
				return HorizontalAlignment.center;
			case "l":
				return HorizontalAlignment.left;
			case "r":
				return HorizontalAlignment.right;
			default: 
				throw new IllegalArgumentException(c + " is not a supported text justification value");
			}
	       
	    }

}
