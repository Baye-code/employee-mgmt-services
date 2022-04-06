package org.laminf.code.service;

import org.laminf.code.model.Employee;
import org.laminf.code.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEmployeeImpl implements IEmployee {

    @Autowired
    EmployeeRepository repository;

    @Override
    public Employee create(Employee o) {
        return repository.save(o);
    }


    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Employee update(long id, Employee o) {
        return null;
    }

    @Override
    public void delete(Employee o) {

    }

    @Override
    public Employee update(long id, Object o) {
        Employee emp = (Employee) o;
        Employee e = getById(id);
        if (e != null) {
            e.setFullName(emp.getFullName());
            e.setEmployeeNumber(emp.getEmployeeNumber());
            e.setDepartmentCode(emp.getDepartmentCode());
            e.setManager(emp.isManager());
            return repository.save(e);
        }
        return emp;
    }

    @Override
    public void delete(Object o) {
        repository.delete((Employee) o);
    }

}
