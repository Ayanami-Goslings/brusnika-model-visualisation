package ru.brusnika.model.domain;

import lombok.Data;
import java.util.List;

@Data
public class Group {
    private List<Employee> employees;
    private Department department;
    private Division division;
    private Location location;
}