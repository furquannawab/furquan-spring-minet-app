package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
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
    @Column(name = "total_quantity")
    private float totalQuantity;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "transaction_fee")
    private int transactionFee;

    public Transaction(@NonNull int userId, @NonNull int assetId, float totalQuantity, String transactionType, int transactionFee) {
        this.userId = userId;
        this.assetId = assetId;
        this.totalQuantity = totalQuantity;
        this.transactionType = transactionType;
        this.transactionFee = transactionFee;
    }
}
