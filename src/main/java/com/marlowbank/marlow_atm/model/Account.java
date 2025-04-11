package com.marlowbank.marlow_atm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // Relationship with transactions
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();

    // Relationship with users (for joint accounts)
    @ManyToMany
    @JoinTable(
            name = "account_users",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

}
