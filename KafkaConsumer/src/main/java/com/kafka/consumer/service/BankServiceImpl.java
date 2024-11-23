package com.kafka.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.dto.BankTransactionPayload;
import com.kafka.consumer.entity.BankTransaction;
import com.kafka.consumer.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BankServiceImpl implements BankService {

  private final TransactionRepository transactionRepository;

  @Override
  public void sendMoney(BankTransactionPayload bankTransactionPayload) {
    if (bankTransactionPayload.getMoney() > 1000) {
      throw new RuntimeException("Amount exceeds the limit");
    }
    log.info(
        "Preparing for sending money from {} to {} with amount {}",
        bankTransactionPayload.getOriginAccountId(),
        bankTransactionPayload.getDestinationAccountId(),
        bankTransactionPayload.getMoney());
  }

  @Override
  public BankTransaction saveTransaction(BankTransaction bankTransaction)
      throws JsonProcessingException {
    try {
      BankTransaction bankTransactionSaved = transactionRepository.save(bankTransaction);
      log.info("Transaction saved: {}", new ObjectMapper().writeValueAsString(bankTransaction));
      return bankTransactionSaved;
    } catch (Exception e) {
      log.error("An error has occured trying to save transaction");
      throw e;
    }
  }
}
