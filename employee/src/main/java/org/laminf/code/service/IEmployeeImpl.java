package org.laminf.code.service;


import org.laminf.code.clients.department.Department;
import org.laminf.code.clients.notification.NotificationRequest;
import org.laminf.code.dto.Notification;
import org.laminf.code.model.Employee;
import org.laminf.code.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEmployeeImpl implements IEmployee {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${departmentService.url}")
    private String departmentServiceURL;

    @Value("${notificationService.url}")
    private String notificationServiceURL;

    @Override
    public void create(Employee o) {

        // Retrieving Department by the Id
        Department dept = restTemplate
                .getForObject(departmentServiceURL + o.getDepartmentId(), Department.class);

        // Setting departement Code for the employee
        o.setDepartmentCode(dept.getDepartmentCode());
        repository.saveAndFlush(o);

        // Sending Notification after saving new Employee
        Integer EmployeeId = Integer.parseInt(String.valueOf(o.getId()));
        NotificationRequest notificationRequest = new NotificationRequest(
                EmployeeId,
                o.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        o.getFullName())
        );

        Notification notification = restTemplate
                .postForObject(notificationServiceURL, notificationRequest, Notification.class);

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
