package com.kafka.producer.utils;

public class Constants {

  public static final String NUMERIC_REGEX = "^[A-Za-z0-9]{10}$";
  public static final String ALPHANUMERIC_ERROR_MESSAGE = "Origin account id must be 10 alphanumeric characters";
  public static final String TRANSACTION_TYPE_ERROR_MESSAGE = "Transaction type must be one of TRANSFER, DEPOSIT, or WITHDRAWAL";
  public static final String TRANSACTION_TYPE_REGEX = "^(TRANSFER|DEPOSIT|WITHDRAWAL)$";
  public static final String MONEY_AMOUNT_ERROR_MESSAGE = "Money amount must be greater than 0.01";
  public static final Long MIN_MONEY_AMOUNT =(long) 1;
}
