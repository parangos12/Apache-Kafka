package com.kafka.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.consumer.dto.BankTransactionPayload;
import com.kafka.consumer.entity.BankTransaction;

public interface BankService {

  void sendMoney(BankTransactionPayload bankTransactionPayload);

  BankTransaction saveTransaction(BankTransaction bankTransaction) throws JsonProcessingException;
}
