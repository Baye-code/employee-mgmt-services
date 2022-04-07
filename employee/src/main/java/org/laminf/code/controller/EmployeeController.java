package org.laminf.code.controller;

import org.laminf.code.dto.Department;
import org.laminf.code.model.Employee;
import org.laminf.code.service.IEmployeeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    IEmployeeImpl service;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${departmentService.url}")
    private String departmentServiceURL;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee emp) {
        return new ResponseEntity<>(service.create(emp), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable long id, @RequestBody Employee emp) {
        return new ResponseEntity<>(service.update(id, emp), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(service.getById(id));
    }

}