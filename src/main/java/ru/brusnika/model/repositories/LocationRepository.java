package ru.brusnika.model.repositories;

import org.springframework.stereotype.Repository;
import ru.brusnika.model.domain.Location;

@Repository
public interface LocationRepository extends ExtendedJpaRepository<Location, Long> {
}