package com.vensai.vtrack.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vensai.vtrack.Utils.UpgradePassword;
import com.vensai.vtrack.authorisation.dto.AuthorizationRequest;
import com.vensai.vtrack.securityconfig.JwtService;
import com.vensai.vtrack.services.Vtrackservices;
import com.vensai.vtrack.udt.employee.EmployeeDetails;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class Vtrackrestcontroller {

	@Autowired
	EmployeeDetails employeedetails;

	@Autowired
	Vtrackservices vtrackservices;

	@Autowired
	private JwtService jwtservice;

	@Autowired
	private AuthenticationManager authManager;

	@GetMapping("/getdetails/{EmployeeID}")
	public EmployeeDetails getDetails(@PathVariable String EmployeeID) {
		employeedetails = vtrackservices.getEmployeebyId(EmployeeID);
		return employeedetails;
	}

	@GetMapping("/getdata")
	public Optional<EmployeeDetails> getDetailswithToken(@RequestHeader("Authorization") String jwtToken) {

		System.out.println("Data request entred");
		String cleantoken = jwtToken.substring(6);
		String Email = jwtservice.validateToken(cleantoken);
		
		System.out.println(Email + "Email Extracted from the token");

		return vtrackservices.getEmployeebyEmail(Email);

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/login")
	public String getEmployeedata(@RequestBody AuthorizationRequest authrequest) {
		System.out.println("Auth request entred");
		
		Authentication authenticate = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authrequest.getEmail(), authrequest.getPassword()));
		System.out.println(authenticate);
		if (authenticate.isAuthenticated()) {

			return jwtservice.generateToken(authrequest.getEmail());
		} else {
			throw new UsernameNotFoundException("Invalid User request");
		}
//		return employeedetails;

	}
	
	
	

	@PostMapping("/updatepassword")
	public String updateEmployeePassword(@RequestBody EmployeeDetails empdetails) {

		return vtrackservices.updatePassword(empdetails);

	}

}
