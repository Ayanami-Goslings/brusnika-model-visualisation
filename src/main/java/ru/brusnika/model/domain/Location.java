package ru.brusnika.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Table(name = "brusnika_table")
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String name;
    @OneToMany(mappedBy = "locationId")
    private List<Employee> employees;
    @OneToMany(mappedBy = "locationId")
    private List<Group> groups;
    @OneToMany(mappedBy = "locationId")
    private List<Department> departments;
    @OneToMany(mappedBy = "locationId")
    private List<Division> divisions;
}
