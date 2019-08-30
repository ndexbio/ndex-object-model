package org.ndexbio.model.tools;

public class SearchUtilities {
	
	/**
	 * This function replaces comma with a whitespace if it is double quoted
	 * It is used by Solr searches in NDEx to treat comma as a special character.
	 * @param searchString
	 * @return
	 */
	public static String preprocessSearchTerm(String searchString) {
		return searchString.replaceAll("(,)(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", " ");
	}

}
