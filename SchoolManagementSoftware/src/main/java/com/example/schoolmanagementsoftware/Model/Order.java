package com.example.schoolmanagementsoftware.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Expression;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "User_Order")
public class Order implements jakarta.persistence.criteria.Order {
    @Id
    @SequenceGenerator(name = "id", sequenceName = "idOrder", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "idOrder", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Positive
    @NotNull(message = " quantity be not null")
    @Column(columnDefinition = "int")
    private Integer quantity;
    @Positive
    @NotNull(message = " totalPrice be not null")
    @Column(columnDefinition = "numeric(8,2)")
    private Double totalPrice;

    @NotNull(message = " must be not null")
    @Column(columnDefinition = "date")
    @FutureOrPresent
    private LocalDate dateReceived;

    @NotBlank(message = "status must have (new or inProgress or completed)")
    @Column(columnDefinition = "varchar(50) check(status = 'new' or status = 'inProgress' or status = 'completed')")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    public Order(Integer quantity,
                 Double totalPrice,
                 LocalDate dateReceived,
                 String status,
                 Customer customer,
                 Product product)
{
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.dateReceived = dateReceived;
        this.status = status;
        this.customer = customer;
        this.product = product;
    }

    @Override
    public jakarta.persistence.criteria.Order reverse() {
        return null;
    }

    @Override
    public boolean isAscending() {
        return false;
    }

    @Override
    public Expression<?> getExpression() {
        return null;
    }
}
