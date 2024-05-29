package ru.brusnika.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.brusnika.model.domain.DTO.NodeDTO;
import ru.brusnika.model.service.ExcelImportService;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    private ExcelImportService excelImportService;

    @GetMapping("/nodesWithEdges")
    public List<NodeDTO> getNodesWithEdges() {
        return excelImportService.getAllNodesWithEdges();
    }
}