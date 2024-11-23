package com.kafka.consumer.streaming.consumer;

import static org.hamcrest.Matchers.equalTo;

import com.kafka.consumer.dto.BankTransactionPayload;
import com.kafka.consumer.entity.BankTransaction;
import com.kafka.consumer.repository.TransactionRepository;
import com.kafka.consumer.service.BankService;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureEmbeddedDatabase
@DirtiesContext
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = {"listeners=PLAINTEXT://localhost:9094", "port=9094"})
class KafkaConsumerApplicationTests {

  @Autowired private TransactionRepository transactionRepository;
  @Autowired KafkaTemplate<String, String> kafkaTemplate;
  @Autowired BankService bankService;
  @Autowired ObjectMapper objectMapper;

  @Test
  @Sql({"/sql/ddl/transanction_ddl.sql"})
  void processStoreMessageSuccessfully() throws IOException {

    BankTransaction expected = new BankTransaction("P645444031", "AB52891827", "DEPOSIT", 900.0);

    String stringPayload =
        Files.readString(
            Paths.get("src/test/resources/files/transaction.json"), StandardCharsets.UTF_8);

    BankTransactionPayload bankTransactionPayload =
        objectMapper.readValue(stringPayload, BankTransactionPayload.class);

    kafkaTemplate.send(
        new ProducerRecord<>("transactions-test-topic", "A645444032", stringPayload));

    Awaitility.await()
        .atMost(20, TimeUnit.SECONDS)
        .pollInterval(1, TimeUnit.SECONDS)
        .until(transactionRepository::count, equalTo(1L));

    BankTransaction actual = transactionRepository.findById("A645444032").get();

    Assertions.assertEquals(expected, actual);
  }
}
