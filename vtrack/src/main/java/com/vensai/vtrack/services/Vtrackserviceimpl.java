package com.vensai.vtrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vensai.vtrack.repository.EmployeeDetailRepository;
import com.vensai.vtrack.udt.employee.EmployeeDetails;

@Service
public class Vtrackserviceimpl implements Vtrackservices {

	@Autowired
	EmployeeDetailRepository employeedetailRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public EmployeeDetails getEmployeebyId(String EmpID) {
		// TODO Auto-generated method stub
		return employeedetailRepo.findByEmpId(EmpID);
	}

	@Override
	public Optional<EmployeeDetails> getEmployeebyEmail(String Email) {
		// TODO Auto-generated method stub
		return employeedetailRepo.findByEmail(Email);
	}

	@Override
	public String updatePassword(EmployeeDetails empdetails) {
		
		String password = empdetails.getEmpPassword();
		empdetails = employeedetailRepo.findByEmpId(empdetails.getEmpId());
		empdetails.setEmpPassword(encoder.encode(password));
		employeedetailRepo.save(empdetails);
		
		return "Password updated successfully  for :" + empdetails.getEmpId();
	}
	
	public void upgradePassword()
	{
		List<EmployeeDetails> EmployeesList = employeedetailRepo.findAll();
		for(EmployeeDetails emp :EmployeesList) {
//			emp.setEmpPassword());
			emp.setEmpPassword(encoder.encode(emp.getEmpId()));
			employeedetailRepo.save(emp);
			System.out.println("Passowrd Upgraded successfully for "+ emp.getEmpId());
		}
		
		
	}

}
