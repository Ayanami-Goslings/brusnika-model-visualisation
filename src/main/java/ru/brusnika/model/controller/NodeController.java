package ru.brusnika.model.controller;

import ru.brusnika.model.domain.DTO.NodeDTO;
import ru.brusnika.model.domain.Node;
import ru.brusnika.model.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    @Autowired
    private NodeRepository nodeRepository;

    @GetMapping
    public List<NodeDTO> getAllNodes() {
        List<Node> nodes = nodeRepository.findAll();
        return nodes.stream().map(node -> {
            if ("Employee".equals(node.getType())) {
                return new NodeDTO(node.getId(), node.getName(), node.getType(), node.getPosition(), node.getWorkType());
            } else {
                return new NodeDTO(node.getId(), node.getName(), node.getType());
            }
        }).collect(Collectors.toList());
    }
}
