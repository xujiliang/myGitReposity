package com.my.sssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.my.sssp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee getByLastName(String lastName);
}
