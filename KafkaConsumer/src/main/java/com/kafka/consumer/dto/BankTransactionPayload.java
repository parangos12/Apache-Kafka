package com.kafka.consumer.dto;

import static com.kafka.consumer.utils.Constants.ALPHANUMERIC_ERROR_MESSAGE;
import static com.kafka.consumer.utils.Constants.MIN_MONEY_AMOUNT;
import static com.kafka.consumer.utils.Constants.MONEY_AMOUNT_ERROR_MESSAGE;
import static com.kafka.consumer.utils.Constants.NUMERIC_REGEX;
import static com.kafka.consumer.utils.Constants.TRANSACTION_TYPE_ERROR_MESSAGE;
import static com.kafka.consumer.utils.Constants.TRANSACTION_TYPE_REGEX;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankTransactionPayload {

  @NotNull(message = "Origin account id is required")
  @Pattern(regexp = NUMERIC_REGEX, message = ALPHANUMERIC_ERROR_MESSAGE)
  private String originAccountId;

  @NotNull(message = "Destination account id is required")
  @Pattern(regexp = NUMERIC_REGEX, message = ALPHANUMERIC_ERROR_MESSAGE)
  private String destinationAccountId;

  @NotNull(message = "Transaction type is required")
  @Pattern(regexp = TRANSACTION_TYPE_REGEX, message = TRANSACTION_TYPE_ERROR_MESSAGE)
  private String transactionType;

  @Min(value = MIN_MONEY_AMOUNT, message = MONEY_AMOUNT_ERROR_MESSAGE)
  private double money;
}
