package org.ndexbio.cx2.converter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.ndexbio.model.exceptions.NdexException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class CXToCX2ConverterTest {

/*	@Test
	public void test0() throws JsonParseException, JsonMappingException, IOException, NdexException {
		
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		
		CXToCX2Converter cvt = new CXToCX2Converter (
				absolutePath + "/60medge_sample.cx",absolutePath + "/60medges_2_conf.json", "test1.cx");
		cvt.convert();
	}  
	
	
	@Test
	public void test2() throws JsonParseException, JsonMappingException, IOException, NdexException {
		
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		
		CXToCX2Converter cvt = new CXToCX2Converter (
				absolutePath + "/Anton_private.cx",absolutePath + "/Anton_private_conf.json", "Anton_private_cx2.cx");
		cvt.convert();
	}

	
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException, NdexException {
		
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		
		CXToCX2Converter cvt = new CXToCX2Converter (
				 "/Users/jingchen/temp/Anton_private_sample.cx",
				   absolutePath + "/Anton_private_conf.json", "Anton_private_sample_cx2.cx");
		cvt.convert();
		
		CXToCX2Converter cvt2 = new CXToCX2Converter (
				 "/Users/jingchen/temp/sample_with_continues_mappings.cx",
				   absolutePath + "/60medges_2_conf.json", "sample_with_continues_mappings_cx2.cx");
		cvt2.convert();
		
		cvt2 = new CXToCX2Converter (
				   absolutePath + "/Node_edge_bypasses.cx",
				   absolutePath + "/general_conf.json", "Node_edge_bypasses_cx2.cx");
		cvt2.convert();
	}  
	
	*/
	
	
	@Test
	public void test3() throws JsonMappingException, IOException, NdexException {
		
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();

		CXToCX2Converter cvt2;
		
		cvt2 = new CXToCX2Converter (
				 "/Users/jingchen/temp/NeST_raw1_wstyle.cx",
				null, "kei_test.cx2");
		cvt2.convert(); 

		/* cvt2 = new CXToCX2Converter (
				 "/Users/jingchen/temp/STRING - Human Protein Links - High Confidence (Score _= 0.7) grid layout.cx",
				 absolutePath + "/STRING_HUMAN_conf.json", "string_0_7.cx2");
		cvt2.convert(); 
		*/
	/*	Converter cvt1 = new Converter (
				 "/Users/jingchen/temp/go_bp_drugcell_min10_merge30_depth5_ontology.cx",
				 absolutePath + "/STRING_HUMAN_conf.json", "go_bp_drugcell_min10_merge30_depth5_ontology.cx2");
		cvt1.convert(); */
		
		/*CXToCX2Converter cvt11 = new CXToCX2Converter (
				 "/Users/jingchen/temp/go_bp_drugcell_min10_merge30_depth5_ontology.cx",
				 absolutePath + "/go_bp_drugcell_conf.json", "go_bp_drugcell_min10_merge30_depth5_ontology.cx2");
		cvt11.convert(); 
		*/
		
/*		Converter cvt11 = new Converter (
				 "/Users/jingchen/temp/string-community.cx",
				 absolutePath + "/general_conf.json", "string-community.cx2");
		cvt11.convert();
		
		
		cvt11 = new Converter (
				 "/Users/jingchen/temp/pcnet-large.cx",
				 absolutePath + "/general_conf.json", "pcnet-large.cx2");
		cvt11.convert();
*/		
	/*	Converter cvt = new Converter (
				 "/Users/jingchen/temp/60medge.cx",absolutePath + "/60medges_2_conf.json", "60medge.cx2");
		cvt.convert();  
*/
	/*	Converter cvt01 = new Converter (
				 "/Users/jingchen/temp/60medge.cx",absolutePath + "/60medges_2_conf.json", "60medge_2.cx2");
		cvt01.convert(); 
*/
	/*	Converter cvt2 = new Converter (
				 "/Users/jingchen/temp/STRING - Human Protein Links.cx",absolutePath + "/STRING_HUMAN_conf.json", "string.cx2");
		cvt2.convert(); 

		
		Converter cvt3 = new Converter (
				 "/Users/jingchen/temp/STRING - Human Protein Links.cx", absolutePath + "/STRING_HUMAN_aliased_attr_conf.json", 
				 "string2.cx2");
		
		cvt3.convert(); 
*/
	}

}
