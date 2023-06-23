package com.vensai.vtrack.udt.employee;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmloyeeLoginDetails implements UserDetails {
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;


    public EmloyeeLoginDetails(EmployeeDetails employeeDetails) {
        name = employeeDetails.getEmail();
        password = employeeDetails.getEmpPassword();
        System.out.println("EmployeeDetails object injected: " + employeeDetails);
        authorities = Arrays.stream(employeeDetails.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify this as per your application's logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify this as per your application's logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify this as per your application's logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify this as per your application's logic
    }
}
