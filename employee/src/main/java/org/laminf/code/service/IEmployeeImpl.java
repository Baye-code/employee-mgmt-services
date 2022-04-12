package org.laminf.code.service;


import org.laminf.code.clients.department.Department;
import org.laminf.code.clients.department.DepartmentClient;
import org.laminf.code.clients.notification.NotificationClient;
import org.laminf.code.clients.notification.NotificationRequest;
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
    private final NotificationClient notificationClient;

    @Value("${departmentService.url}")
    private String departmentServiceURL;

    public IEmployeeImpl(DepartmentClient departmentClient, NotificationClient notificationClient) {
        this.departmentClient = departmentClient;
        this.notificationClient = notificationClient;
    }

    @Override
    public void create(Employee o) {

        // getting the Id from Department
        long id = Long.parseLong(o.getDepartmentId());

        // Retrieving Department by the Id
        ResponseEntity<Department> dept = departmentClient.getById(id);

        // Setting departement Code for the employee
        String deptCode = dept.getBody().getDepartmentCode();
        o.setDepartmentCode(deptCode);
        repository.saveAndFlush(o);

        // Sending Notification after saving new Employee
        Integer EmployeeId = Integer.parseInt(String.valueOf(o.getId()));
        NotificationRequest notificationRequest = new NotificationRequest(
                EmployeeId,
                o.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        o.getFullName())
        );
        notificationClient.sendNotification(
                notificationRequest
        );

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
