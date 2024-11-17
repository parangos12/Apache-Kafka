package com.kafka.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransactionPayload {

  private String originAccountId;

  private String destinationAccountId;

  private String transactionType;

  private double money;
}
