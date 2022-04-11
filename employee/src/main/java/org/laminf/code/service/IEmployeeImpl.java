package org.laminf.code.service;

import org.laminf.code.config.DepartmentClient;
import org.laminf.code.dto.Department;
import org.laminf.code.model.Employee;
import org.laminf.code.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEmployeeImpl implements IEmployee {

    @Autowired
    private EmployeeRepository repository;

    private final DepartmentClient departmentClient;

    @Value("${departmentService.url}")
    private String departmentServiceURL;

    public IEmployeeImpl(DepartmentClient departmentClient) {
        this.departmentClient = departmentClient;
    }

    @Override
    public Employee create(Employee o) {
        long id = Long.parseLong(o.getDepartmentId());
        ResponseEntity<Department> dept = departmentClient.getById(id);
        String deptCode = dept.getBody().getDepartmentCode();
        o.setDepartmentCode(deptCode);
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
