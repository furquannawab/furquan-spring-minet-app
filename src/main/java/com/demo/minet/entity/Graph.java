package com.demo.minet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "graph")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Graph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "x")
    private int x;
    @Column(name = "y")
    private int y;
    @ManyToOne
    @JoinColumn(name = "asset_detail_id")
    private AssetDetail assetDetail;
}
