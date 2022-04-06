package org.laminf.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDepartmentImpl implements IDepartment {

    @Autowired
    DepartmentRepository repository;

    @Override
    public Department create(Department o) {
        return repository.save((Department) o);
    }

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }

    @Override
    public Department getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Department update(long id, Department o) {

        Department d = getById(id);
        if (d != null) {
            d.setName(o.getName());
            return repository.save(d);
        }

        return o;
    }

    @Override
    public void delete(Department o) {
        repository.delete(o);
    }
}
