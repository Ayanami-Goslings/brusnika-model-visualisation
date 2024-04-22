package ru.brusnika.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Table(name = "brusnika_table")
@Data
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String name;
    @OneToMany(mappedBy = "divisionId")
    private List<Employee> employees;
    @OneToMany(mappedBy = "divisionId")
    private List<Group> groups;
    @OneToMany(mappedBy = "divisionId")
    private List<Department> departments;
    @Column(name = "Location")
    private Long locationId;
}