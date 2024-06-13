package ru.brusnika.model.domain.DTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeDTO {
    private Long id;
    private String name;
    private String type;
    private NodeDTO target;
    private String position;
    private String workType;

    public NodeDTO(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public NodeDTO(Long id, String name, String type, NodeDTO target) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.target = target;
    }

    public NodeDTO(Long id, String name, String type, String position, String workType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.position = position;
        this.workType = workType;
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

    public NodeDTO getTarget() {
        return target;
    }

    public void setTarget(NodeDTO target) {
        this.target = target;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}

