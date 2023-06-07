package com.example.schoolmanagementsoftware.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Setter
@Getter
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idUser")
    @SequenceGenerator(name = "idUser", sequenceName = "idUser", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "Username not be empty")
    @Column(unique = true, columnDefinition = "varchar(100)")
    private String username;

    @NotEmpty(message = "Password not be empty")
    @Pattern(regexp = "(.^|!|&||&||^!^||&||&|!|^.)", message = "be at least 10 character,\"^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$\")")
    @NotEmpty(message = "must not be empty")
    private String password;


    @Column(columnDefinition = "varchar(50) check(role in ('ADMIN', 'CUSTOMER'))")
    @NotEmpty(message = "role not be empty")
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> singleton = Collections.singleton(new SimpleGrantedAuthority(role));
        return singleton;
    }


    @Override

    public boolean isAccountNonExpired() {
        return true;
    }


    @Override

    public boolean isAccountNonLocked() {
        return true;
    }


    @Override

    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override

    public boolean isEnabled() {
        return true;
    }

}