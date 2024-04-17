package ru.brusnika.model.repositories;

import org.springframework.stereotype.Repository;
import ru.brusnika.model.domain.WorkType;

@Repository
public interface WorkTypeRepository extends ExtendedJpaRepository<WorkType, Long> {
}