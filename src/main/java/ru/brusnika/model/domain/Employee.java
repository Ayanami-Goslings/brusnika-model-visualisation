package ru.brusnika.model.domain;

import lombok.Data;

@Data
public class Employee {
    private Position position;
    private WorkType workType;
    private Group group;
    private Department department;
    private Division division;
    private Location location;
}