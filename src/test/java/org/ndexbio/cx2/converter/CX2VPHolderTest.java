package org.ndexbio.cx2.converter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.Test;
import org.ndexbio.cx2.aspect.element.core.CxEdgeBypass;
import org.ndexbio.cx2.aspect.element.core.CxNodeBypass;
import org.ndexbio.cx2.aspect.element.core.CxVisualProperty;
import org.ndexbio.cx2.aspect.element.core.DefaultVisualProperties;

/**
 *
 * @author churas
 */
public class CX2VPHolderTest {
    
    @Test
    public void testGettersAndSetters(){
        CX2VPHolder holder = new CX2VPHolder();
        CxVisualProperty visProp = holder.getStyle();
        assertNotNull(visProp);
        assertTrue(visProp.isEmpty());
        
        List<CxNodeBypass> nodeByPasses = holder.getNodeBypasses();
        assertTrue(nodeByPasses.isEmpty());
        List<CxEdgeBypass> edgeByPasses = holder.getEdgeBypasses();
        assertTrue(edgeByPasses.isEmpty());
        
        CxVisualProperty newVis = new CxVisualProperty();
        DefaultVisualProperties defaultProps = new DefaultVisualProperties();
        Map<String, Object> networkProperties = new HashMap<>();
        networkProperties.put("hi", "there");
        defaultProps.setNetworkProperties(networkProperties);
        newVis.setDefaultProps(defaultProps);
        holder.setStyle(newVis);
        
        CxNodeBypass nodeByPass = new CxNodeBypass();
        nodeByPass.setId(5);
        holder.setNodeBypasses(Arrays.asList(nodeByPass));
        
        CxEdgeBypass edgeByPass = new CxEdgeBypass();
        edgeByPass.setId(6);
        holder.setEdgeBypasses(Arrays.asList(edgeByPass));
        
        CxVisualProperty resVis = holder.getStyle();
        assertTrue(resVis.getDefaultProps().getNetworkProperties().containsKey("hi"));
   
        
        assertEquals(5, holder.getNodeBypasses().get(0).getId());
        
        assertEquals(6, holder.getEdgeBypasses().get(0).getId());
        
        
    }
    
}
