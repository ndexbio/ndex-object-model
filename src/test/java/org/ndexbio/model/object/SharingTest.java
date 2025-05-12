package org.ndexbio.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SharingTest {
    
    static ObjectMapper mapper;
    
    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }
    
    @Test
    public void testSharedFile() throws JsonProcessingException {
        SharedFile original = new SharedFile();
        UUID fileId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();
        String ownerName = "testUser";
        
        original.setUuid(fileId);
        original.setType(FileType.NETWORK);
        original.setOwnerId(ownerId);
        original.setOwner(ownerName);
        
        FileItemSummary summary = new FileItemSummary();
        summary.setName("Test Network");
        summary.setType(FileType.NETWORK);
        summary.setUuid(fileId);
        original.setFileSummary(summary);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized SharedFile: " + json);
        
        SharedFile deserialized = mapper.readValue(json, SharedFile.class);
        
        assertEquals(deserialized.getUuid(), original.getUuid());
        assertEquals(deserialized.getType(), original.getType());
        assertEquals(deserialized.getOwnerId(), original.getOwnerId());
        assertEquals(deserialized.getOwner(), original.getOwner());
        assertEquals(deserialized.getFileSummary().getName(), original.getFileSummary().getName());
        assertEquals(deserialized.getFileSummary().getType(), original.getFileSummary().getType());
        assertEquals(deserialized.getFileSummary().getUuid(), original.getFileSummary().getUuid());
    }
    
    @Test
    public void testSharingSimpleRequest() throws JsonProcessingException {
        SharingSimpleRequest original = new SharingSimpleRequest();
        Map<UUID, FileType> files = new HashMap<>();
        UUID fileId1 = UUID.randomUUID();
        UUID fileId2 = UUID.randomUUID();
        
        files.put(fileId1, FileType.NETWORK);
        files.put(fileId2, FileType.FOLDER);
        original.setFiles(files);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized SharingSimpleRequest: " + json);
        
        SharingSimpleRequest deserialized = mapper.readValue(json, SharingSimpleRequest.class);
        
        assertEquals(deserialized.getFiles().size(), original.getFiles().size());
        assertEquals(deserialized.getFiles().get(fileId1), FileType.NETWORK);
        assertEquals(deserialized.getFiles().get(fileId2), FileType.FOLDER);
    }
    
    @Test
    public void testSharingMemberRequest() throws JsonProcessingException {
        SharingMemberRequest original = new SharingMemberRequest();
        Map<UUID, FileType> files = new HashMap<>();
        Map<UUID, Permissions> members = new HashMap<>();
        
        UUID fileId = UUID.randomUUID();
        UUID memberId1 = UUID.randomUUID();
        UUID memberId2 = UUID.randomUUID();
        
        files.put(fileId, FileType.NETWORK);
        members.put(memberId1, Permissions.READ);
        members.put(memberId2, Permissions.WRITE);
        
        original.setFiles(files);
        original.setMembers(members);
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized SharingMemberRequest: " + json);
        
        SharingMemberRequest deserialized = mapper.readValue(json, SharingMemberRequest.class);
        
        assertEquals(deserialized.getFiles().size(), original.getFiles().size());
        assertEquals(deserialized.getFiles().get(fileId), FileType.NETWORK);
        
        assertEquals(deserialized.getMembers().size(), original.getMembers().size());
        assertEquals(deserialized.getMembers().get(memberId1), Permissions.READ);
        assertEquals(deserialized.getMembers().get(memberId2), Permissions.WRITE);
    }
    
    @Test
    public void testSharedFileWithNullValues() throws JsonProcessingException {
        SharedFile original = new SharedFile();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized SharedFile with nulls: " + json);
        
        SharedFile deserialized = mapper.readValue(json, SharedFile.class);
        
        assertEquals(deserialized.getUuid(), original.getUuid());
        assertEquals(deserialized.getType(), original.getType());
        assertEquals(deserialized.getOwnerId(), original.getOwnerId());
        assertEquals(deserialized.getOwner(), original.getOwner());
        assertEquals(deserialized.getFileSummary(), original.getFileSummary());
    }
    
    @Test
    public void testSharingSimpleRequestWithNullValues() throws JsonProcessingException {
        SharingSimpleRequest original = new SharingSimpleRequest();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized SharingSimpleRequest with nulls: " + json);
        
        SharingSimpleRequest deserialized = mapper.readValue(json, SharingSimpleRequest.class);
        
        assertEquals(deserialized.getFiles(), original.getFiles());
    }
    
    @Test
    public void testSharingMemberRequestWithNullValues() throws JsonProcessingException {
        SharingMemberRequest original = new SharingMemberRequest();
        
        String json = mapper.writeValueAsString(original);
        System.out.println("Serialized SharingMemberRequest with nulls: " + json);
        
        SharingMemberRequest deserialized = mapper.readValue(json, SharingMemberRequest.class);
        
        assertEquals(deserialized.getFiles(), original.getFiles());
        assertEquals(deserialized.getMembers(), original.getMembers());
    }
}
