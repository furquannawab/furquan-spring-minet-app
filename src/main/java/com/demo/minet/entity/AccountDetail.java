package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "account_no")
    private int accountNo;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "ifsc_code")
    private String ifscCode;
    @Column(name = "account_balance")
    private float accountBalance;
}
