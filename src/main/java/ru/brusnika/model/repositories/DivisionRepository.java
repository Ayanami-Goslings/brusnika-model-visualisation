package ru.brusnika.model.repositories;

import org.springframework.stereotype.Repository;
import ru.brusnika.model.domain.Division;

@Repository
public interface DivisionRepository extends ExtendedJpaRepository<Division, Long> {
}