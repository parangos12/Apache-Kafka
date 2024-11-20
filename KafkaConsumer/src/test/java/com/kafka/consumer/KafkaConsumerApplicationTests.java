package com.kafka.consumer;

import static org.hamcrest.Matchers.equalTo;

import com.kafka.consumer.dto.BankTransactionPayload;
import com.kafka.consumer.service.BankService;
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

@ActiveProfiles("test")
@SpringBootTest
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = {"listeners=PLAINTEXT://localhost:9093", "port=9093"})
class KafkaConsumerApplicationTests {

  @Autowired KafkaTemplate<String, String> kafkaTemplate;
  @Autowired BankService bankService;

  @Test
  void processStoreMessageSuccessfully() throws IOException {

    BankTransactionPayload expected =
        new BankTransactionPayload("P645444031", "AB52891827", "DEPOSIT", 900.0);

    String payload =
        Files.readString(
            Paths.get("src/test/resources/files/transaction.json"), StandardCharsets.UTF_8);

    kafkaTemplate.send(
        new ProducerRecord<>("transactions-test-topic", String.valueOf(706), payload));

    Awaitility.await()
        .atMost(10, TimeUnit.SECONDS)
        .pollInterval(1, TimeUnit.SECONDS)
        .until(() -> true, equalTo(true));

    BankTransactionPayload actual = bankService.saveTransaction(expected);

    Assertions.assertEquals(expected, actual);
  }
}
