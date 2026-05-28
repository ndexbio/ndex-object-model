package org.ndexbio.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ndexbio.model.object.network.VisibilityType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileTest {
    
    static ObjectMapper mapper;
    
    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }
    
    @Test
    public void testFileCount() throws JsonProcessingException {
        FileCount original = new FileCount();
        original.setNetwork(5);
        original.setFolder(3);
        original.setShortcut(2);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FileCount: " + json);
        
        FileCount deserialized = mapper.readValue(json, FileCount.class);
        
        assertEquals(deserialized.getNetwork(), original.getNetwork());
        assertEquals(deserialized.getFolder(), original.getFolder());
        assertEquals(deserialized.getShortcut(), original.getShortcut());
    }
    
    @Test
    public void testFileItemSummary() throws JsonProcessingException {
        FileItemSummary original = new FileItemSummary();
        UUID fileId = UUID.randomUUID();
        Timestamp modTime = new Timestamp(System.currentTimeMillis());
        
        original.setUuid(fileId);
        original.setType(FileType.NETWORK);
        original.setName("Test Network");
        original.setModificationTime(modTime);
        original.setUpdatedBy("testUser");
        original.setOwner("ownerUser");
        UUID ownerId = UUID.randomUUID();
        original.setOwnerId(ownerId);
        original.setVisibility("PUBLIC");
        original.setEdges(42);
        original.setPermission("READ");
        
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("key1", "value1");
        attributes.put("key2", 123);
        original.setAttributes(attributes);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FileItemSummary: " + json);
        
        FileItemSummary deserialized = mapper.readValue(json, FileItemSummary.class);
        
        assertEquals(deserialized.getUuid(), original.getUuid());
        assertEquals(deserialized.getType(), original.getType());
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getModificationTime(), original.getModificationTime());
        assertEquals(deserialized.getUpdatedBy(), original.getUpdatedBy());
        assertEquals(deserialized.getOwner(), original.getOwner());
        assertEquals(deserialized.getOwnerId(), original.getOwnerId());
        assertEquals(deserialized.getVisibility(), original.getVisibility());
        assertEquals(deserialized.getEdges(), original.getEdges());
        assertEquals(deserialized.getPermission(), original.getPermission());
        assertEquals(deserialized.getAttributes().get("key1"), original.getAttributes().get("key1"));
        assertEquals(deserialized.getAttributes().get("key2"), original.getAttributes().get("key2"));
        assertNull(deserialized.getAttributes().get("owner"));
        assertNull(deserialized.getAttributes().get("owner_id"));
        assertNull(deserialized.getAttributes().get("permission"));
    }
    
    @Test
    public void testFileVisibilityRequest() throws JsonProcessingException {
        FileVisibilityRequest original = new FileVisibilityRequest();
        Map<UUID, FileType> files = new HashMap<>();
        UUID fileId1 = UUID.randomUUID();
        UUID fileId2 = UUID.randomUUID();
        
        files.put(fileId1, FileType.NETWORK);
        files.put(fileId2, FileType.FOLDER);
        
        original.setVisibility(VisibilityType.PUBLIC);
        original.setFiles(files);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FileVisibilityRequest: " + json);
        
        FileVisibilityRequest deserialized = mapper.readValue(json, FileVisibilityRequest.class);
        
        assertEquals(deserialized.getVisibility(), original.getVisibility());
        assertEquals(deserialized.getFiles().size(), original.getFiles().size());
        assertEquals(deserialized.getFiles().get(fileId1), FileType.NETWORK);
        assertEquals(deserialized.getFiles().get(fileId2), FileType.FOLDER);
    }
    
    @Test
    public void testFileCountWithZeroValues() throws JsonProcessingException {
        FileCount original = new FileCount();
        original.setNetwork(0);
        original.setFolder(0);
        original.setShortcut(0);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FileCount with zeros: " + json);
        
        FileCount deserialized = mapper.readValue(json, FileCount.class);
        
        assertEquals(deserialized.getNetwork(), original.getNetwork());
        assertEquals(deserialized.getFolder(), original.getFolder());
        assertEquals(deserialized.getShortcut(), original.getShortcut());
    }
    
    @Test
    public void testFileItemSummaryWithNullValues() throws JsonProcessingException {
        FileItemSummary original = new FileItemSummary();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FileItemSummary with nulls: " + json);
        
        FileItemSummary deserialized = mapper.readValue(json, FileItemSummary.class);
        
        assertEquals(deserialized.getUuid(), original.getUuid());
        assertEquals(deserialized.getType(), original.getType());
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getModificationTime(), original.getModificationTime());
        assertEquals(deserialized.getUpdatedBy(), original.getUpdatedBy());
        assertEquals(deserialized.getOwner(), original.getOwner());
        assertEquals(deserialized.getOwnerId(), original.getOwnerId());
        assertEquals(deserialized.getVisibility(), original.getVisibility());
        assertEquals(deserialized.getEdges(), original.getEdges());
        assertEquals(deserialized.getPermission(), original.getPermission());
        assertEquals(deserialized.getAttributes(), original.getAttributes());
    }
    
    @Test
    public void testFileVisibilityRequestWithNullValues() throws JsonProcessingException {
        FileVisibilityRequest original = new FileVisibilityRequest();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FileVisibilityRequest with nulls: " + json);
        
        FileVisibilityRequest deserialized = mapper.readValue(json, FileVisibilityRequest.class);
        
        assertEquals(deserialized.getVisibility(), original.getVisibility());
        assertEquals(deserialized.getFiles(), original.getFiles());
    }
    
    @Test
    public void testFileItemSummaryConstructor() throws JsonProcessingException {
        UUID fileId = UUID.randomUUID();
        String name = "Test Network";
        FileItemSummary original = new FileItemSummary(fileId, FileType.NETWORK, name);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized FileItemSummary from constructor: " + json);
        
        FileItemSummary deserialized = mapper.readValue(json, FileItemSummary.class);
        
        assertEquals(deserialized.getUuid(), fileId);
        assertEquals(deserialized.getType(), FileType.NETWORK);
        assertEquals(deserialized.getName(), name);
    }
}
