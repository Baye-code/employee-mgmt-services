package org.laminf.code.controller;

import lombok.extern.slf4j.Slf4j;
import org.laminf.code.model.Employee;
import org.laminf.code.service.IEmployeeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    IEmployeeImpl service;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public void create(@RequestBody Employee emp) {
        log.info("new Employee registration {}", emp);
        service.create(emp);
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