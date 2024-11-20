package com.kafka.consumer.service;

import com.kafka.consumer.dto.BankTransactionPayload;

public interface BankService {

  void sendMoney(BankTransactionPayload bankTransactionPayload);

  BankTransactionPayload saveTransaction(BankTransactionPayload bankTransaction);
}
