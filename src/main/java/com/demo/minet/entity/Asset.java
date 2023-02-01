package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asset")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "asset_name")
    private String assetName;
    @Column(name = "asset_code")
    private String assetCode;
    @Column(name = "description")
    private String description;
    @Column(name = "resources")
    private String resources;
    @OneToOne(mappedBy = "asset")
    private AssetDetail assetDetail;
}
