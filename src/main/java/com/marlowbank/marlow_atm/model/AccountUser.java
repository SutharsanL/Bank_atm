package com.marlowbank.marlow_atm.model;

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
    private Account account;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
}