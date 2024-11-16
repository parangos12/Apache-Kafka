package com.kafka.producer.dto;

import static com.kafka.producer.utils.Constants.ALPHANUMERIC_ERROR_MESSAGE;
import static com.kafka.producer.utils.Constants.MONEY_AMOUNT_ERROR_MESSAGE;
import static com.kafka.producer.utils.Constants.NUMERIC_REGEX;
import static com.kafka.producer.utils.Constants.TRANSACTION_TYPE_ERROR_MESSAGE;
import static com.kafka.producer.utils.Constants.TRANSACTION_TYPE_REGEX;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
  @Pattern(regexp = NUMERIC_REGEX, message = "Origin "+ALPHANUMERIC_ERROR_MESSAGE)
  private String originAccountId;

  @NotNull(message = "Destination account id is required")
  @Pattern(regexp = NUMERIC_REGEX, message = "Destination "+ALPHANUMERIC_ERROR_MESSAGE)
  private String destinationAccountId;

  @NotNull(message = "Transaction type is required")
  @Pattern(regexp = TRANSACTION_TYPE_REGEX, message = TRANSACTION_TYPE_ERROR_MESSAGE)
  private String transactionType;

  @Min(value = 1, message = MONEY_AMOUNT_ERROR_MESSAGE)
  private double money;
}
