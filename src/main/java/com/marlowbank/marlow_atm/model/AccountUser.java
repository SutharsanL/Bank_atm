package com.marlowbank.marlow_atm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account_user")
public class AccountUser {

    @EmbeddedId
    private AccountUserId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    @JsonBackReference(value = "account-reference")
    private Account account;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-reference")
    private User user;
}