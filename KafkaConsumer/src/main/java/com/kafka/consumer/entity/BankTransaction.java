package com.kafka.consumer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_transaction")
public class BankTransaction {

  @Id
  @Column(name="origin_account_id")
  private String originAccountId;

  @Column(name="destination_account_id")
  private String destinationAccountId;

  @Column(name="transaction_type")
  private String transactionType;

  @Column(name="money")
  private double money;
}
