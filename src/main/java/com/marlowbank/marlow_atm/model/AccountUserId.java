package com.marlowbank.marlow_atm.model;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Data
@Embeddable
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Required for @Builder
@Builder // Generates a builder for the class
public class AccountUserId implements Serializable {

    private Long accountId;
    private Long userId;
}
