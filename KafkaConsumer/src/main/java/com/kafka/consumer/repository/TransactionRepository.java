package com.kafka.consumer.repository;

import com.kafka.consumer.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<BankTransaction, String> {}
