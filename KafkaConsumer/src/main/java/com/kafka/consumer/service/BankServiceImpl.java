package com.kafka.consumer.service;

import com.kafka.consumer.dto.BankTransactionPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BankServiceImpl implements BankService {

  @Override
  public void sendMoney(BankTransactionPayload bankTransactionPayload) {
    log.info(
        "Sending money from {} to {} with amount {}",
        bankTransactionPayload.getOriginAccountId(),
        bankTransactionPayload.getDestinationAccountId(),
        bankTransactionPayload.getMoney());
    if(bankTransactionPayload.getMoney() > 1000) {
      throw new RuntimeException("Amount exceeds the limit");
    }
  }
}
