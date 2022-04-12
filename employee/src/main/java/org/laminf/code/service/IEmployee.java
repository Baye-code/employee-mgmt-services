package org.laminf.code.service;

import org.laminf.code.model.Employee;

import java.util.List;

public interface IEmployee {

    void create(Employee o);

    List<Employee> getAll();

    Employee getById(long id);

    Employee update(long id, Employee o);

    void delete(Employee o);

    Employee update(long id, Object o);

    void delete(Object o);

}
