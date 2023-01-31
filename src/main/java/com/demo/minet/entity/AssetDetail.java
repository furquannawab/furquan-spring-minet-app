package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "asset_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "price")
    private float price;
    @Column(name = "change")
    private String change;
    @Column(name = "market_cap")
    private String marketCap;
    @Column(name = "circulating_supply")
    private String circulatingSupply;
    @OneToOne
    @JoinColumn(name = "id")
    private Asset asset;
    @OneToMany(mappedBy = "assetDetail")
    private List<Graph> graphs;
}
