package org.laminf.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    IDepartmentImpl service;

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Department> getById(@PathVariable long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department dept) {
        return new ResponseEntity<>(service.create(dept), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable long id, @RequestBody Department dept) {
        return new ResponseEntity<>(service.update(id, dept), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(service.getById(id));
    }
}