package com.demo.minet.dao;

import com.demo.minet.entity.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDetail, Integer> {
}
