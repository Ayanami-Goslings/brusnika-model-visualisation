package ru.brusnika.model.repository;

import ru.brusnika.model.domain.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdgeRepository extends JpaRepository<Edge, Long> {
}