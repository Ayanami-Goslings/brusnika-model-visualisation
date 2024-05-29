package ru.brusnika.model.service;

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
import java.util.List;

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
            for (Row row : sheet) {
                String location = getCellValue(row, 0);
                String subdivision = getCellValue(row, 1);
                String department = getCellValue(row, 2);
                String group = getCellValue(row, 3);
                String employee = getCellValue(row, 4);

                Node locationNode = addNode(location, "Location");
                Node divisionNode = addNode(subdivision, "Subdivision");
                Node departmentNode = addNode(department, "Department");
                Node groupNode = addNode(group, "Group");
                Node employeeNode = addNode(employee, "Employee");

                addEdge(locationNode, divisionNode);
                addEdge(divisionNode, departmentNode);
                addEdge(departmentNode, groupNode);
                addEdge(groupNode, employeeNode);
            }
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

    private Node addNode(String name, String type) {
        if (name == null || name.isEmpty()) return null;
        List<Node> existingNodes = nodeRepository.findByNameAndType(name, type);
        if (!existingNodes.isEmpty()) {
            return existingNodes.get(0);
        } else {
            Node newNode = new Node();
            newNode.setName(name);
            newNode.setType(type);
            return nodeRepository.save(newNode);
        }
    }

    private void addEdge(Node source, Node target) {
        if (source == null || target == null) return;
        Edge edge = new Edge();
        edge.setSource(source);
        edge.setTarget(target);
        edgeRepository.save(edge);
    }
}