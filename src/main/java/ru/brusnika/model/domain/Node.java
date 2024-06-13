package ru.brusnika.model.domain;

import jakarta.persistence.*;

@Entity
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    private String position;
    private String workType;

    public Node(){}

    public Node(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Node(Long id, String name, String type, String position, String workType) {
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