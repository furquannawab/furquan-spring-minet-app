package com.demo.minet.rest;

import com.demo.minet.entity.Transaction;
import com.demo.minet.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionRestController.class)
class TransactionRestControllerTest {

    @MockBean
    TransactionService transactionService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    static Transaction transaction;

    @BeforeAll
    public static void startup() {
        transaction = new Transaction(1, 1, 1, 0.1f, "SOLD", 100);
    }

    @Test
    void getAllTransactions() throws Exception {
        List<Transaction> transactions = new ArrayList<>(
                List.of(transaction)
        );

        when(transactionService.getAllTransactions()).thenReturn(transactions);
        mvc.perform(MockMvcRequestBuilders.get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(transactions.size()))
                .andDo(print());
    }

    @Test
    void getTransactionById() throws Exception {
        when(transactionService.getTransactionById(1)).thenReturn(Optional.of(transaction));
        mvc.perform(MockMvcRequestBuilders.get("/transactions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transaction.getId()))
                .andExpect(jsonPath("$.userId").value(transaction.getUserId()))
                .andExpect(jsonPath("$.assetId").value(transaction.getAssetId()))
                .andExpect(jsonPath("$.totalQuantity").value(transaction.getTotalQuantity()))
                .andExpect(jsonPath("$.transactionType").value(transaction.getTransactionType()))
                .andExpect(jsonPath("$.transactionFee").value(transaction.getTransactionFee()))
                .andDo(print());
    }

    @Test
    void saveTransaction() throws Exception {
        when(transactionService.saveTransaction(transaction)).thenReturn(transaction);
        mvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}