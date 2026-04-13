package org.ndexbio.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FolderTest {
    
    static ObjectMapper mapper;
    
    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }
    
    @Test
    public void testFolder() throws JsonProcessingException {
        NdexFolder original = new NdexFolder();
        UUID parentId = UUID.randomUUID();
        String folderName = "Test Folder";
        String owner = "owner1";
        
        original.setName(folderName);
        original.setParent(parentId);
        original.setOwner_id(owner);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized Folder: " + json);
        
        NdexFolder deserialized = mapper.readValue(json, NdexFolder.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
        assertEquals(deserialized.getOwner_id(), original.getOwner_id());
    }
    
    @Test
    public void testFolderRequest() throws JsonProcessingException {
        FolderRequest original = new FolderRequest();
        UUID parentId = UUID.randomUUID();
        String folderName = "New Folder";
        
        original.setName(folderName);
        original.setParent(parentId);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FolderRequest: " + json);
        
        FolderRequest deserialized = mapper.readValue(json, FolderRequest.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
    }
    
    @Test
    public void testFolderWithNullValues() throws JsonProcessingException {
        NdexFolder original = new NdexFolder();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized Folder with nulls: " + json);
        
        NdexFolder deserialized = mapper.readValue(json, NdexFolder.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
        assertEquals(deserialized.getOwner_id(), original.getOwner_id());
    }
    
    @Test
    public void testFolderRequestWithNullValues() throws JsonProcessingException {
        FolderRequest original = new FolderRequest();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FolderRequest with nulls: " + json);
        
        FolderRequest deserialized = mapper.readValue(json, FolderRequest.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
    }
}
