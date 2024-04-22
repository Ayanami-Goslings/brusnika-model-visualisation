package ru.brusnika.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Table(name = "brusnika_table")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String name;
    @OneToMany(mappedBy = "groupId")
    private List<Employee> employees;
    @Column(name = "Department")
    private Long departmentId;
    @Column(name = "Division")
    private Long divisionId;
    @Column(name = "Location")
    private Long locationId;
}