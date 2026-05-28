package org.ndexbio.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrashTest {
    
    static ObjectMapper mapper;
    
    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }
    
    @Test
    public void testTrashRestoreRequest() throws JsonProcessingException {
        TrashRestoreRequest original = new TrashRestoreRequest();
        List<UUID> networks = new ArrayList<>();
        List<UUID> folders = new ArrayList<>();
        List<UUID> shortcuts = new ArrayList<>();
        
        networks.add(UUID.randomUUID());
        networks.add(UUID.randomUUID());
        folders.add(UUID.randomUUID());
        shortcuts.add(UUID.randomUUID());
        
        original.setNetworks(networks);
        original.setFolders(folders);
        original.setShortcuts(shortcuts);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized TrashRestoreRequest: " + json);
        
        TrashRestoreRequest deserialized = mapper.readValue(json, TrashRestoreRequest.class);
        
        assertEquals(deserialized.getNetworks().size(), networks.size());
        assertEquals(deserialized.getNetworks().get(0), networks.get(0));
        assertEquals(deserialized.getNetworks().get(1), networks.get(1));
        assertEquals(deserialized.getFolders().size(), folders.size());
        assertEquals(deserialized.getFolders().get(0), folders.get(0));
        assertEquals(deserialized.getShortcuts().size(), shortcuts.size());
        assertEquals(deserialized.getShortcuts().get(0), shortcuts.get(0));
    }
    
    @Test
    public void testTrashRestoreRequestWithEmptyLists() throws JsonProcessingException {
        TrashRestoreRequest original = new TrashRestoreRequest();
        List<UUID> networks = new ArrayList<>();
        List<UUID> folders = new ArrayList<>();
        List<UUID> shortcuts = new ArrayList<>();
        
        original.setNetworks(networks);
        original.setFolders(folders);
        original.setShortcuts(shortcuts);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized TrashRestoreRequest with empty lists: " + json);
        
        TrashRestoreRequest deserialized = mapper.readValue(json, TrashRestoreRequest.class);
        
        assertEquals(deserialized.getNetworks().size(), 0);
        assertEquals(deserialized.getFolders().size(), 0);
        assertEquals(deserialized.getShortcuts().size(), 0);
    }
}
