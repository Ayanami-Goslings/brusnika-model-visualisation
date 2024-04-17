package ru.brusnika.model.repositories;

import org.springframework.stereotype.Repository;
import ru.brusnika.model.domain.Employee;

@Repository
public interface EmployeeRepository extends ExtendedJpaRepository<Employee, Long> {
}