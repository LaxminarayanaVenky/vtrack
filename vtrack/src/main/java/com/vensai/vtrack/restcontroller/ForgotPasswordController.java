package com.vensai.vtrack.restcontroller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vensai.vtrack.authorisation.dto.ResetPassword;
import com.vensai.vtrack.repository.EmployeeDetailRepository;
import com.vensai.vtrack.services.Vtrackservices;
import com.vensai.vtrack.udt.employee.EmployeeDetails;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ForgotPasswordController {
	@Autowired
	private Vtrackservices vtrackServices;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmployeeDetailRepository empRepo;
	
	@Autowired
	 private PasswordEncoder encoder;

	@PostMapping("/forgotpassword/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable("email") String email) {
		Optional<EmployeeDetails> employee = vtrackServices.getEmployeebyEmail(email);

		if (employee == null) {
			return ResponseEntity.badRequest().body("User not found");
		}

		// Generate a password reset token
		String token = generateToken();

		employee.get().setResetToken(token);

		// Save the token to the user entity
//        u(token);
//        userService.save(user);

		empRepo.save(employee.get());

//         Send the password reset email
		sendResetEmail(employee.get().getEmail(), token);

		return ResponseEntity.ok("Password reset email sent");
	}

	private void sendResetEmail(String email, String token) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Password Reset");
		message.setText("To reset your password, click the link below:\n\n"
				+ "Reset Link: http://localhost:3000/resetpassword?token=" + token);

		mailSender.send(message);
	}

	private String generateToken() {
		// Generate a unique token using a library or your own logic
		// Example using UUID:
		return UUID.randomUUID().toString();
	}
	

	
	@PostMapping("/resetpassword")
	public String resetPassword(@RequestBody ResetPassword passwordrequest) {
		
		Optional<EmployeeDetails> empDetails = empRepo.findByResetToken(passwordrequest.getToken());
		
		if(empDetails.isPresent()) {
			empDetails.get().setEmpPassword(encoder.encode(passwordrequest.getNewPassword()));
			empDetails.get().setResetToken(null);
			empRepo.save(empDetails.get());
			System.out.println("Passowrd updated for the account"+ empDetails.get().getEmail());
			return "Password updated succesfully";
		}
		return "Invalid token";
		
		
	}
}
