package ru.brusnika.model.DTO;

import lombok.Data;
import java.util.List;

@Data
public class GraphDTO {

    private List<String> nodes;
    private List<GraphEdgeDTO> edges;

    public GraphDTO(List<String> nodes, List<GraphEdgeDTO> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
}