package com.demo.minet.service;

import com.demo.minet.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    public Transaction saveTransaction(Transaction transaction);
    public List<Transaction> getAllTransactions();
    public Optional<Transaction> getTransactionById(int id);
}
