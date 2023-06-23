package com.vensai.vtrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vensai.vtrack.udt.employee.EmployeeDetails;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetails, String>  {
 
	EmployeeDetails findByEmpId(String EmpID);
	
	Optional<EmployeeDetails> findByEmail(String Email);
}
