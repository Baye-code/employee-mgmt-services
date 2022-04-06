package org.laminf.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fullName;

    @Column(unique = true, name = "emp_no", length = 50)
    private String employeeNumber;

    @Transient
    @Column(name = "department_code")
//    @JsonIgnore
    private String departmentCode;

    @Transient
    @Column(name = "department_id")
//    @JsonIgnore
    private String departmentId;

    private boolean manager;

}
