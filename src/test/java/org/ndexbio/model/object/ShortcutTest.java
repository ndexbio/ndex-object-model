package org.ndexbio.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShortcutTest {
    
    static ObjectMapper mapper;
    
    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }
    
    @Test
    public void testShortcut() throws JsonProcessingException {
        NdexShortcut original = new NdexShortcut();
        UUID parentId = UUID.randomUUID();
        UUID targetId = UUID.randomUUID();
        String shortcutName = "Test Shortcut";
        
        original.setName(shortcutName);
        original.setParent(parentId);
        original.setTarget(targetId);
        original.setTargetType(FileType.NETWORK);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized Shortcut: " + json);
        
        NdexShortcut deserialized = mapper.readValue(json, NdexShortcut.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
        assertEquals(deserialized.getTarget(), original.getTarget());
        assertEquals(deserialized.getTargetType(), original.getTargetType());
    }
    
    @Test
    public void testShortcutRequest() throws JsonProcessingException {
        ShortcutRequest original = new ShortcutRequest();
        UUID parentId = UUID.randomUUID();
        UUID targetId = UUID.randomUUID();
        String shortcutName = "New Shortcut";
        
        original.setName(shortcutName);
        original.setParent(parentId);
        original.setTarget(targetId);
        original.setTargetType(FileType.NETWORK);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized ShortcutRequest: " + json);
        
        ShortcutRequest deserialized = mapper.readValue(json, ShortcutRequest.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
        assertEquals(deserialized.getTarget(), original.getTarget());
        assertEquals(deserialized.getTargetType(), original.getTargetType());
    }
    
    @Test
    public void testShortcutWithNullValues() throws JsonProcessingException {
        NdexShortcut original = new NdexShortcut();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized Shortcut with nulls: " + json);
        
        NdexShortcut deserialized = mapper.readValue(json, NdexShortcut.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
        assertEquals(deserialized.getTarget(), original.getTarget());
        assertEquals(deserialized.getTargetType(), original.getTargetType());
    }
    
    @Test
    public void testShortcutRequestWithNullValues() throws JsonProcessingException {
        ShortcutRequest original = new ShortcutRequest();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized ShortcutRequest with nulls: " + json);
        
        ShortcutRequest deserialized = mapper.readValue(json, ShortcutRequest.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
        assertEquals(deserialized.getTarget(), original.getTarget());
        assertEquals(deserialized.getTargetType(), original.getTargetType());
    }
    
    @Test
    public void testShortcutWithDifferentTargetTypes() throws JsonProcessingException {
        NdexShortcut original = new NdexShortcut();
        UUID parentId = UUID.randomUUID();
        UUID targetId = UUID.randomUUID();
        String shortcutName = "Test Shortcut";
        
        original.setName(shortcutName);
        original.setParent(parentId);
        original.setTarget(targetId);
        original.setTargetType(FileType.FOLDER);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized Shortcut with FOLDER type: " + json);
        
        NdexShortcut deserialized = mapper.readValue(json, NdexShortcut.class);
        
        assertEquals(deserialized.getName(), original.getName());
        assertEquals(deserialized.getParent(), original.getParent());
        assertEquals(deserialized.getTarget(), original.getTarget());
        assertEquals(deserialized.getTargetType(), FileType.FOLDER);
    }
}
