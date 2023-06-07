package com.example.schoolmanagementsoftware.Security;

import com.example.schoolmanagementsoftware.Model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerSecurity implements UserDetailsService {
    private final CustomerSecurity customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.hashCode (username);

        if(customer==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }

        return customer;
    }

    private Customer hashCode(String username) {
        return null;
    }

}