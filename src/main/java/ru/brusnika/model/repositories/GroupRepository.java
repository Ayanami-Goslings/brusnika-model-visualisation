package ru.brusnika.model.repositories;

import org.springframework.stereotype.Repository;
import ru.brusnika.model.domain.Group;

@Repository
public interface GroupRepository extends ExtendedJpaRepository<Group, Long> {
}