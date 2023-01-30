package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "asset_id")
    private int assetId;
    @Column(name = "total_amount")
    private int totalAmount;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "paying_through")
    private String payingThrough;
    @Column(name = "deposit_to")
    private String depositTo;
}
