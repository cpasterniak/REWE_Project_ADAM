import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        // Konfiguration des Kafka Consumers
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka Broker
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // Kafka Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Abonniere das Topic
        consumer.subscribe(Collections.singletonList("my-topic"));

        // Konsumiere Nachrichten
        while (true) {
            var records = consumer.poll(java.time.Duration.ofMillis(100));  // Poll für Nachrichten
            records.forEach(record -> {
                System.out.printf("Nachricht empfangen: Key=%s, Value=%s%n", record.key(), record.value());
            });
        }
    }
}

