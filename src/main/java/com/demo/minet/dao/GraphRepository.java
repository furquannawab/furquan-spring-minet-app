package com.demo.minet.dao;

import com.demo.minet.entity.Graph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphRepository extends JpaRepository<Graph, Integer> {
}
