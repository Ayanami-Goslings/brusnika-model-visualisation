package ru.brusnika.model.controller;

import ru.brusnika.model.domain.Edge;
import ru.brusnika.model.repository.EdgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/edges")
public class EdgeController {

    @Autowired
    private EdgeRepository edgeRepository;

    @GetMapping
    public List<Edge> getAllEdges() {
        return edgeRepository.findAll();
    }
}