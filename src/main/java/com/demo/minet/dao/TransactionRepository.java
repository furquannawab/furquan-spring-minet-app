package com.demo.minet.dao;

import com.demo.minet.entity.Asset;
import com.demo.minet.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
