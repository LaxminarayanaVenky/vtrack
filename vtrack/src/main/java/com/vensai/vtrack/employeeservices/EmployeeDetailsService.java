package com.vensai.vtrack.employeeservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.vensai.vtrack.repository.EmployeeDetailRepository;
import com.vensai.vtrack.udt.employee.EmloyeeLoginDetails;
import com.vensai.vtrack.udt.employee.EmployeeDetails;

	

@Component
public class EmployeeDetailsService implements UserDetailsService{
	@Autowired
	private EmployeeDetailRepository employeedetailRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	    Optional<EmployeeDetails> userinfo = employeedetailRepo.findByEmail(username);
	    if (userinfo ==null) {
	    	System.out.println("Search with UserID");
	    	userinfo =	Optional.of(employeedetailRepo.findByEmpId(username));
	    }
	    System.out.println("object present: " + userinfo.isPresent());
	    
	    return userinfo.map(EmloyeeLoginDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found in the database: " + username));
	}

	

}
