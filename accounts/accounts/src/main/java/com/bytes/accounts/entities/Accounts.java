package com.bytes.accounts.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Accounts extends BaseEntity{



    private Long customerId;
    @Id
    private long accountNumber;
    private String accountType;
    private String branchAddress;


}
