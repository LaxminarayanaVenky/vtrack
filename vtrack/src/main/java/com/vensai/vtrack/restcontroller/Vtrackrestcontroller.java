package com.vensai.vtrack.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vensai.vtrack.udt.employee.EmployeeDetails;

@RestController
public class Vtrackrestcontroller {
	
	@Autowired
	EmployeeDetails employeedetails;
	
	@GetMapping("/getdetails")
	public EmployeeDetails getDetails() {
		
		return  employeedetails;
	}

}
