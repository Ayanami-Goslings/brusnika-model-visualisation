package ru.brusnika.model.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "brusnika_table")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String name;
    @Column(name = "Position")
    private Long positionId;
    @Column(name = "Type_of_work")
    private Long workTypeId;
    @Column(name = "Group")
    private Long groupId;
    @Column(name = "Department")
    private Long departmentId;
    @Column(name = "Division")
    private Long divisionId;
    @Column(name = "Location")
    private Long locationId;
}