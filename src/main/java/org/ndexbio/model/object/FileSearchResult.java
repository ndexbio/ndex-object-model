package org.ndexbio.model.object;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileSearchResult {
    private long numFound;
    private long start;
    private List<FileItemSummary> files;
    
    public FileSearchResult() {
        this.files = new ArrayList<>();
    }
    
    public FileSearchResult(long numberOfResult, long startAt, List<FileItemSummary> fileList) {
        this.numFound = numberOfResult;
        this.start = startAt;
        this.files = fileList;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public List<FileItemSummary> getFiles() {
        return files;
    }

    public void setFiles(List<FileItemSummary> files) {
        this.files = files;
    }
} 