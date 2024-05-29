package ru.brusnika.model.domain.DTO;

import java.util.List;

public class ParsedGraphDataDTO {
    private NodeDTO source;
    private List<NodeDTO> targets;

    public ParsedGraphDataDTO(NodeDTO source, List<NodeDTO> targets) {
        this.source = source;
        this.targets = targets;
    }

    public NodeDTO getSource() {
        return source;
    }

    public void setSource(NodeDTO source) {
        this.source = source;
    }

    public List<NodeDTO> getTargets() {
        return targets;
    }

    public void setTargets(List<NodeDTO> targets) {
        this.targets = targets;
    }
}