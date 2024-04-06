package ru.brusnika.model.domain;

import lombok.Data;

import java.util.List;

@Data
public class Location {
    private List<Employee> employees;
    private List<Group> groups;
    private List<Department> departments;
    private List<Division> divisions;
}
