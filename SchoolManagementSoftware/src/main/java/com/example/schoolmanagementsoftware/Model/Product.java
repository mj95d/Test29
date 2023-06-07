package com.example.schoolmanagementsoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
    public class Product {
        @Id
        @GeneratedValue(generator = "idProduct", strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(name = "id", sequenceName = "idProduct", initialValue = 1, allocationSize = 1)
        private Integer id;


        @Column(columnDefinition = "varchar(60) unique")
        @NotEmpty(message = "name not empty")
        private String name;

        @NotNull(message = "price not null")
        @Column(columnDefinition = "FLOAT")
        @Positive
        private Double price;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
        private Set<Order> orders;

    }