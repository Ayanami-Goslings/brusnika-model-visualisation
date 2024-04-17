package ru.brusnika.model.repositories;

import org.springframework.stereotype.Repository;
import ru.brusnika.model.domain.Position;

@Repository
public interface PositionRepository extends ExtendedJpaRepository<Position, Long> {
}