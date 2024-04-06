package ru.brusnika.model.domain;

import lombok.Data;

import java.util.List;

@Data
public class Division {
    private List<Employee> employees;
    private List<Group> groups;
    private List<Department> departments;
    private Location location;
}