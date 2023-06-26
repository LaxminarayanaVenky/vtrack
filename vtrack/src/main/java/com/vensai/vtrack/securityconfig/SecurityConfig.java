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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vensai.vtrack.Jwt.JwtRequestFilter;
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

////	 Authentication manager
//	@Bean
//	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
//
//		return http.csrf().disable().authorizeHttpRequests()
//				.requestMatchers("/login", "/updatepassword", "/getdetails/{emailId}", "/getdata",
//						"/getPassword/{EmployeeID}")
//				.permitAll().and().authorizeHttpRequests().requestMatchers("/").permitAll().and()
//				.authorizeHttpRequests().requestMatchers("/home").authenticated().and().authorizeHttpRequests()
//				.requestMatchers("/admin").authenticated().and().build();
//
//	}

	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configure(http)).csrf(csrf -> csrf.disable()).authorizeHttpRequests(
				auth -> auth.requestMatchers("/login", "/updatepassword", "/getPassword/{EmployeeID}","/forgotpassword/{email}","/resetpassword").permitAll())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/getdetails/{emailId}", "/login").permitAll())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/home", "/getdata").authenticated());

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

	@Bean
	public JwtRequestFilter authenticationJwtTokenFilter() {
		return new JwtRequestFilter();
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
