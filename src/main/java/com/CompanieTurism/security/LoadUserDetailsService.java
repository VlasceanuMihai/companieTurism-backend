package com.CompanieTurism.security;

import com.CompanieTurism.models.Employee;
import com.CompanieTurism.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoadUserDetailsService implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    @Autowired
    protected LoadUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = this.employeeRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee with " + username + " not found!"));

        return new UserPrincipal(
                employee.getId(),
                username,
                employee.getPassword(),
                employee.getRole());
    }
}
