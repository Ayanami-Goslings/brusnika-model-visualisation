package ru.brusnika.model.domain.DTO;

import java.util.List;

public class NodeDTO {
    private Long id;
    private String name;
    private String type;
    private List<NodeDTO> targets;

    public NodeDTO(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public NodeDTO(Long id, String name, String type, List<NodeDTO> targets) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.targets = targets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NodeDTO> getTargets() {
        return targets;
    }

    public void setTargets(List<NodeDTO> targets) {
        this.targets = targets;
    }
}

