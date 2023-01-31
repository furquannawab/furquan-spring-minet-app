package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NonNull
    @Column(name = "user_id")
    private int userId;
    @NonNull
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
