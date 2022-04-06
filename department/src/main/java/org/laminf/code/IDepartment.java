package org.laminf.code;

import java.util.List;

public interface IDepartment {

    Department create(Department o);

    List<Department> getAll();

    Department getById(long id);

    Department update(long id, Department o);

    void delete(Department o);
}
