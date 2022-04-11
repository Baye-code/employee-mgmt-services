package org.laminf.code.clients.department;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "department"
//        url = "${clients.department.url}"
)
public interface DepartmentClient {

    @GetMapping(path = "api/v1/departments/{id}")
    public ResponseEntity<Department> getById(@PathVariable long id);

}
