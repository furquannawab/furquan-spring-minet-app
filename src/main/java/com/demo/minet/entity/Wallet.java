package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wallet")
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "asset_id")
    private int assetId;
    @Column(name = "total_balance")
    private int totalBalance;

}
