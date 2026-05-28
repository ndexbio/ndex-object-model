package org.ndexbio.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CopyTest {
    
    static ObjectMapper mapper;
    
    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }
    
    @Test
    public void testCopyRequest() throws JsonProcessingException {
        CopyRequest original = new CopyRequest();
        UUID fileId = UUID.randomUUID();
        UUID targetId = UUID.randomUUID();
        FileType type = FileType.NETWORK;
        
        original.setFileId(fileId);
        original.setTargetId(targetId);
        original.setType(type);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized CopyRequest: " + json);
        
        CopyRequest deserialized = mapper.readValue(json, CopyRequest.class);
        
        assertEquals(deserialized.getFileId(), fileId);
        assertEquals(deserialized.getTargetId(), targetId);
        assertEquals(deserialized.getType(), type);
    }
    
    @Test
    public void testCopyRequestWithShortcut() throws JsonProcessingException {
        CopyRequest original = new CopyRequest();
        UUID fileId = UUID.randomUUID();
        UUID targetId = UUID.randomUUID();
        FileType type = FileType.SHORTCUT;
        
        original.setFileId(fileId);
        original.setTargetId(targetId);
        original.setType(type);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized CopyRequest with shortcut: " + json);
        
        CopyRequest deserialized = mapper.readValue(json, CopyRequest.class);
        
        assertEquals(deserialized.getFileId(), fileId);
        assertEquals(deserialized.getTargetId(), targetId);
        assertEquals(deserialized.getType(), type);
    }
}
