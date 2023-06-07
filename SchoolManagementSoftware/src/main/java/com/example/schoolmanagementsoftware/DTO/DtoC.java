package com.example.schoolmanagementsoftware.DTO;

import com.example.schoolmanagementsoftware.Model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
    @Data
    @AllArgsConstructor
    public class DtoC {
        private String username;
        private String role;
        private Set<Order> orders;
    }
