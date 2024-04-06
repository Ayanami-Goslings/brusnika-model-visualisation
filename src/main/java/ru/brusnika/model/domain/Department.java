package ru.brusnika.model.domain;

import lombok.Data;

import java.util.List;

@Data
public class Department {
    private List<Employee> employees;
    private List<Group> groups;
    private Division division;
    private Location location;
}