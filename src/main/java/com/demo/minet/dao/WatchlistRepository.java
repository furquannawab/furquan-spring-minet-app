package com.demo.minet.dao;

import com.demo.minet.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
}
