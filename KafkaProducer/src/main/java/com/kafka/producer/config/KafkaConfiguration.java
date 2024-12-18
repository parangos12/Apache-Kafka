package com.kafka.producer.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfiguration {

  @Value("${spring.kafka.topic.name}")
  private String topicName;

  @Value("${spring.kafka.topic.partitions}")
  private int partitions;

  @Value("${spring.kafka.topic.replicas}")
  private int replicas;

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public NewTopic generateTopic() {

    Map<String, String> configurations = new HashMap<>();
    configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    configurations.put(TopicConfig.RETENTION_BYTES_CONFIG, "1073741824");
    configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");

    return TopicBuilder.name(topicName)
        .partitions(partitions)
        .replicas(replicas)
        .configs(configurations)
        .build();
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }
}
