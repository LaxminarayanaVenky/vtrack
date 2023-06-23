package com.vensai.vtrack.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vensai.vtrack.employeeservices.EmployeeDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	// Authorization code for users
	@Bean
	public UserDetailsService userDetailservice() {

		return new EmployeeDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

//	 Authentication manager
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {

		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/login","/updatepassword", "/getdetails/{emailId}","/getdata","/getPassword/{EmployeeID}")
				.permitAll().and().authorizeHttpRequests().requestMatchers("/")
				.permitAll().and().authorizeHttpRequests().requestMatchers("/home").authenticated().and()
				.authorizeHttpRequests().requestMatchers("/admin").authenticated().and().build();

	}
	
	
	
	

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationprovider = new DaoAuthenticationProvider();
		authenticationprovider.setUserDetailsService(userDetailservice());
		authenticationprovider.setPasswordEncoder(passwordEncoder());

		return authenticationprovider;

	}

	@Bean
	public AuthenticationManager autenticationManger(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();

	}

}
