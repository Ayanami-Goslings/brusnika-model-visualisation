package ru.brusnika.model.controller;

import ru.brusnika.model.domain.Node;
import ru.brusnika.model.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    @Autowired
    private NodeRepository nodeRepository;

    @GetMapping
    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }
}
