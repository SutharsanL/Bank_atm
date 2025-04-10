package com.marlowbank.marlow_atm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    // Getters & Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(name = "account_number", unique = true, nullable = false, updatable = false)
    private String accountNumber;

    @Setter
    @Column(nullable = false)
    @PositiveOrZero
    private double balance;
    @Setter
    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private Timestamp createdAt;

}
