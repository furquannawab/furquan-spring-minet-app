package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "asset")
@Data
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "price")
    private int price;
    @Column(name = "change")
    private String change;
    @Column(name = "market_cap")
    private String marketCap;
    @Column(name = "asset_name")
    private String assetName;
    @Column(name = "asset_code")
    private String assetCode;
    @Column(name = "description")
    private String description;
    @Column(name = "resources")
    private String resources;
    @Column(name = "circulating_supply")
    private String circulatingSupply;

}
