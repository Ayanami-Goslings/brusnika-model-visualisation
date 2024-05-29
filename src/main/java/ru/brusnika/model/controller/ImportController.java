package ru.brusnika.model.controller;

import ru.brusnika.model.service.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private ExcelImportService excelImportService;

    @GetMapping
    public String importData() {
        try {
            excelImportService.importData("D:/abvgde/bd_brusnika.xlsx");
            return "Data imported successfully";
        } catch (IOException e) {
            return "Error importing data: " + e.getMessage();
        }
    }
}