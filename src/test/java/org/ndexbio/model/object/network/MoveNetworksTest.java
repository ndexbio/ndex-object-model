package org.ndexbio.model.object.network;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.ndexbio.model.object.MoveNetworksRequest;

public class MoveNetworksTest {
    
    static ObjectMapper mapper;
    
    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }
    
    @Test
    public void testMoveNetworksRequest() throws JsonProcessingException {
        MoveNetworksRequest original = new MoveNetworksRequest();
        UUID targetFolder = UUID.randomUUID();
        List<UUID> networks = new ArrayList<>();
        networks.add(UUID.randomUUID());
        networks.add(UUID.randomUUID());
        
        original.setTargetFolder(targetFolder);
        original.setNetworks(networks);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized MoveNetworksRequest: " + json);
        
        MoveNetworksRequest deserialized = mapper.readValue(json, MoveNetworksRequest.class);
        
        assertEquals(deserialized.getTargetFolder(), targetFolder);
        assertEquals(deserialized.getNetworks().size(), networks.size());
        assertEquals(deserialized.getNetworks().get(0), networks.get(0));
        assertEquals(deserialized.getNetworks().get(1), networks.get(1));
    }
    
    @Test
    public void testMoveNetworksRequestWithEmptyList() throws JsonProcessingException {
        MoveNetworksRequest original = new MoveNetworksRequest();
        UUID targetFolder = UUID.randomUUID();
        List<UUID> networks = new ArrayList<>();
        
        original.setTargetFolder(targetFolder);
        original.setNetworks(networks);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized MoveNetworksRequest with empty list: " + json);
        
        MoveNetworksRequest deserialized = mapper.readValue(json, MoveNetworksRequest.class);
        
        assertEquals(deserialized.getTargetFolder(), targetFolder);
        assertEquals(deserialized.getNetworks().size(), 0);
    }
}
