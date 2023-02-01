package com.demo.minet.service;

import com.demo.minet.dao.AccountRepository;
import com.demo.minet.dao.TransactionRepository;
import com.demo.minet.dao.WalletRepository;
import com.demo.minet.entity.AccountDetail;
import com.demo.minet.entity.Asset;
import com.demo.minet.entity.Transaction;
import com.demo.minet.entity.Wallet;
import com.demo.minet.exception.TransactionException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {

        TypedQuery<Wallet> query1 = entityManager.createQuery(
                "from Wallet where userId=:userId and assetId=:assetId", Wallet.class);
        query1.setParameter("userId", transaction.getUserId());
        query1.setParameter("assetId", transaction.getAssetId());
        Wallet wallet = query1.getSingleResult();

        TypedQuery<AccountDetail> query2 = entityManager.createQuery(
                "from AccountDetail where userId=:userId", AccountDetail.class);
        query2.setParameter("userId", transaction.getUserId());
        AccountDetail accountDetail = query2.getSingleResult();

        TypedQuery<Asset> query3 = entityManager.createQuery(
                "from Asset where id=:id", Asset.class
        );
        query3.setParameter("id", transaction.getAssetId());
        Asset asset = query3.getSingleResult();

        float walletQuantity = wallet.getTotalQuantity();
        float accountBalance = accountDetail.getAccountBalance();
        float totalQuantity = transaction.getTotalQuantity();
        float transactionAmount = (totalQuantity * asset.getAssetDetail().getPrice());
        float transactionFee = transaction.getTransactionFee();

        if (transaction.getTransactionType().equals("PURCHASED")) {
            if (accountBalance < transactionAmount + transactionFee)
                throw new TransactionException("Account Balance less than Transaction amount");
            accountBalance -= transactionAmount + transactionFee;
            walletQuantity += totalQuantity;
        } else {
            if (walletQuantity < totalQuantity)
                throw new TransactionException("Wallet Balance less than Transaction amount");
            accountBalance += transactionAmount - transactionFee;
            walletQuantity -= totalQuantity;
        }
        accountDetail.setAccountBalance(accountBalance);
        wallet.setTotalQuantity(walletQuantity);
        accountRepository.save(accountDetail);
        walletRepository.save(wallet);
        Transaction transaction1 = transactionRepository.save(transaction);
        transaction.setId(transaction1.getId());
        return transaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }
}
