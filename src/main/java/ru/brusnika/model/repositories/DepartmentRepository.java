package ru.brusnika.model.repositories;

import org.springframework.stereotype.Repository;
import ru.brusnika.model.domain.Department;

@Repository
public interface DepartmentRepository extends ExtendedJpaRepository<Department, Long> {
}