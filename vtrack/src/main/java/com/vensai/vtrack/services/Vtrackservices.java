package com.vensai.vtrack.services;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vensai.vtrack.udt.employee.EmployeeDetails;

@Component
public interface Vtrackservices {

	EmployeeDetails getEmployeebyId(String EmpID);

	Optional<EmployeeDetails> getEmployeebyEmail(String Email);

	String updatePassword(EmployeeDetails empdetails);
	 void upgradePassword();

}
