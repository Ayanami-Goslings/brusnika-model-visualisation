package ru.brusnika.model.service;

import ru.brusnika.model.domain.DTO.NodeDTO;
import ru.brusnika.model.domain.Edge;
import ru.brusnika.model.domain.Node;
import ru.brusnika.model.repository.EdgeRepository;
import ru.brusnika.model.repository.NodeRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ExcelImportService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private EdgeRepository edgeRepository;

    @Transactional
    public void importData(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Map<String, Node> createdNodes = new HashMap<>();
            long currentId = 1;

            for (Row row : sheet) {
                Node locationNode = addNode(row, 0, "Location", createdNodes, currentId++, null, null);
                Node divisionNode = addNode(row, 1, "Subdivision", createdNodes, currentId++, null, null);
                Node departmentNode = addNode(row, 2, "Department", createdNodes, currentId++, null, null);
                Node groupNode = addNode(row, 3, "Group", createdNodes, currentId++, null, null);
                Node employeeNode = addNode(row, 5, "Employee", createdNodes, currentId++,
                        getCellValue(row, 4), getCellValue(row, 6));

                addEdge(locationNode, divisionNode);
                addEdge(divisionNode, departmentNode);
                addEdge(departmentNode, groupNode);
                addEdge(groupNode, employeeNode);
            }
        }
    }

    private Node addNode(Row row, int cellIndex, String type, Map<String, Node> createdNodes, long id,
                         String position, String workType) {
        String name = getCellValue(row, cellIndex);
        if (name == null || name.isEmpty()) return null;

        String uniqueKey = name + "_" + id;
        if (createdNodes.containsKey(uniqueKey)) {
            return createdNodes.get(uniqueKey);
        } else {
            Node newNode = new Node();
            newNode.setId(id);
            newNode.setName(name);
            newNode.setType(type);
            if ("Employee".equals(type)) {
                newNode.setPosition(position);
                newNode.setWorkType(workType);
            }
            createdNodes.put(uniqueKey, newNode);
            return nodeRepository.save(newNode);
        }
    }

    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            case _NONE:
            case ERROR:
            default:
                return null;
        }
    }

    private void addEdge(Node source, Node target) {
        if (source == null || target == null) return;
        Edge edge = new Edge();
        edge.setSource(source);
        edge.setTarget(target);
        edgeRepository.save(edge);
    }

    public List<NodeDTO> getAllNodesWithEdges() {
        List<Node> nodes = nodeRepository.findAll();
        List<Edge> edges = edgeRepository.findAll();

        Map<Long, NodeDTO> nodeDTOMap = new HashMap<>();
        for (Node node : nodes) {
            if ("Employee".equals(node.getType())) {
                nodeDTOMap.put(node.getId(), new NodeDTO(node.getId(), node.getName(), node.getType(), node.getPosition(), node.getWorkType()));
            } else {
                nodeDTOMap.put(node.getId(), new NodeDTO(node.getId(), node.getName(), node.getType()));
            }
        }

        for (Edge edge : edges) {
            NodeDTO sourceDTO = nodeDTOMap.get(edge.getSource().getId());
            NodeDTO targetDTO = nodeDTOMap.get(edge.getTarget().getId());
            sourceDTO.setTarget(targetDTO);
        }

        return new ArrayList<>(nodeDTOMap.values());
    }
}