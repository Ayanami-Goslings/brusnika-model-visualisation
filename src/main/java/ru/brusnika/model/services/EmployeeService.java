package ru.brusnika.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brusnika.model.domain.Employee;
import ru.brusnika.model.repositories.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    public Employee addEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }
//
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    public Employee getEmployeeById(Long id) {
//        return employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
//    }
//
//    public Employee getEmployeeByName(String name) {
//        return employeeRepository.findByName(name)
//                .orElseThrow(() -> new RuntimeException("Employee not found with name: " + name));
//    }
//
//    public void updateEmployee(Long id, Employee employeeDetails) {
//        Employee employee = getEmployeeById(id);
//        employeeRepository.save(employee);
//    }
//
//    public void deleteEmployee(Long id) {
//        Employee employee = getEmployeeById(id);
//        employeeRepository.delete(employee);
//    }
}