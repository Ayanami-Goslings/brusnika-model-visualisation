package ru.brusnika.model.DTO;

import lombok.Data;

@Data
public class GraphEdgeDTO {

    private String source;
    private String target;

    public GraphEdgeDTO(String source, String target) {
        this.source = source;
        this.target = target;
    }
}