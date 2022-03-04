package com.sg.oracle.sg_management.employee.model.repository;

import com.sg.oracle.sg_management.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String>
{

}
