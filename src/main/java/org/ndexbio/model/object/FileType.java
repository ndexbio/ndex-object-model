package org.ndexbio.model.object;

public enum FileType {
    NETWORK("NETWORK"),
    FOLDER("FOLDER"),
    SHORTCUT("SHORTCUT");
	
	private final String shortName; // Field to store the custom string
	
	FileType(String shortName){
		this.shortName = shortName;
	}
	
	// Override toString() to return the custom string
    @Override
    public String toString() {
        return shortName;
    }
}
