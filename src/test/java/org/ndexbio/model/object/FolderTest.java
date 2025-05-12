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
        Folder original = new Folder();
        UUID parentId = UUID.randomUUID();
        String folderName = "Test Folder";
        
        original.setName(folderName);
        original.setParent(parentId);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized Folder: " + json);
        
        Folder deserialized = mapper.readValue(json, Folder.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
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
        Folder original = new Folder();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized Folder with nulls: " + json);
        
        Folder deserialized = mapper.readValue(json, Folder.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
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
