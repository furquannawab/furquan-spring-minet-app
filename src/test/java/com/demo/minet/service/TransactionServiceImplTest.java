package com.demo.minet.service;

import com.demo.minet.dao.AccountRepository;
import com.demo.minet.dao.TransactionRepository;
import com.demo.minet.dao.WalletRepository;
import com.demo.minet.entity.*;
import com.demo.minet.exception.TransactionException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private EntityManager entityManager;
    @Mock
    TypedQuery<Wallet> walletQuery;
    @Mock
    TypedQuery<AccountDetail> accountQuery;
    @Mock
    TypedQuery<Asset> assetQuery;

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @Test
    void getAllTransactions() {
        Transaction transaction =
                new Transaction(1, 1, 1, 0.1f, "SOLD", 100);
        List<Transaction> transactions = List.of(transaction);
        when(transactionRepository.findAll()).thenReturn(transactions);
        assertThat(transactionService.getAllTransactions()).isEqualTo(transactions);
    }

    @Test
    void getTransactionById() {
        Optional<Transaction> transaction =
                Optional.of(new Transaction(1, 1, 1, 0.1f, "SOLD", 100));
        when(transactionRepository.findById(1)).thenReturn(transaction);
        assertThat(transactionService.getTransactionById(1)).isEqualTo(transaction);
    }

    @Nested
    class saveTransactionTests {
        Wallet wallet;
        AccountDetail accountDetail;
        Asset asset;
        AssetDetail assetDetail;

        @BeforeEach
        public void startup() {
            wallet = new Wallet(1, 1, 1, 0.1f);
            accountDetail = new AccountDetail(1, 1, 123, "ABC", "Bank", "IFSC", 1000);
            assetDetail = new AssetDetail(1, 1000f, "Change", "Cap", "Supply", null, null);
            asset = new Asset(1, "Bitcoin", "BTC", "Desc", "Resc", assetDetail);
            when(entityManager.createQuery(anyString(), eq(Wallet.class))).thenReturn(walletQuery);
            when(entityManager.createQuery(anyString(), eq(AccountDetail.class))).thenReturn(accountQuery);
            when(entityManager.createQuery(anyString(), eq(Asset.class))).thenReturn(assetQuery);
            when(walletQuery.getSingleResult()).thenReturn(wallet);
            when(accountQuery.getSingleResult()).thenReturn(accountDetail);
            when(assetQuery.getSingleResult()).thenReturn(asset);
        }

        @Test
        void saveTransactionForPurchase() {
            Transaction transaction =
                    new Transaction( 1, 1, 0.1f, "PURCHASED", 100);
            when(transactionRepository.save(transaction)).thenReturn(transaction);
            assertThat(transactionService.saveTransaction(transaction)).isEqualTo(transaction);
        }

        @Test
        void saveTransactionForSell() {
            Transaction transaction =
                    new Transaction(1, 1, 0.1f, "SOLD", 100);
            when(transactionRepository.save(transaction)).thenReturn(transaction);
            assertThat(transactionService.saveTransaction(transaction)).isEqualTo(transaction);
        }

        @Test
        void saveTransactionForPurchaseException() {
            Transaction transaction =
                    new Transaction(1, 1, 10f, "PURCHASED", 100);
            assertThatExceptionOfType(TransactionException.class).isThrownBy(() -> transactionService.saveTransaction(transaction));
        }

        @Test
        void saveTransactionForSellException() {
            Transaction transaction =
                    new Transaction(1, 1, 10f, "SOLD", 100);
             assertThatExceptionOfType(TransactionException.class).isThrownBy(() -> transactionService.saveTransaction(transaction));
        }
    }
}
















