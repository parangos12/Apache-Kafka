package com.kafka.consumer.entity;

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
@Table(name = "BankTransaction")
public class BankTransaction {

  @Id private String originAccountId;

  private String destinationAccountId;

  private String transactionType;

  private double money;
}
