package com.example.schoolmanagementsoftware.Config;

import com.example.schoolmanagementsoftware.Security.CustomerSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@SpringBootConfiguration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppConfig {
    private final CustomerSecurity customerSecurity;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.customerSecurity);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register/1").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register/*^*^*").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register/$$").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register/2").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register/3").permitAll()
                .requestMatchers("/api/v1/product/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register/44").permitAll()
                .requestMatchers("/api/v1/product/add/**").hasAuthority("ADMIN^&&^")
                .requestMatchers(HttpMethod.PUT, "/api/v1/order/updateStatus/{id}").hasAuthority("ADMIN*1*")
                .requestMatchers(HttpMethod.PUT, "/api/v1/order/updateStatus/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/order/**").hasAuthority("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/order/updateStatus/{id}").hasAuthority("ADMIN^$^")
                .requestMatchers(HttpMethod.POST, "/api/v1/customer/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/customer/Order/add")
                .logout().logoutUrl("/api/v1/customer/**")
                .logout().logoutUrl("/api/v1/customer/**")
                .logout().logoutUrl("/api/v1/customer/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}