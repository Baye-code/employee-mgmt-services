package org.laminf.code.service;

import org.laminf.code.dto.Department;
import org.laminf.code.model.Employee;
import org.laminf.code.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IEmployeeImpl implements IEmployee {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${departmentService.url}")
    private String departmentServiceURL;

    @Override
    public Employee create(Employee o) {
        Department dept = restTemplate.getForObject(departmentServiceURL + o.getDepartmentId(), Department.class);
        o.setDepartmentCode(dept.getDepartmentCode());
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
