import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) {
        // Kafka configuration
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Kafka Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Convert String[] to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue;
        try {
            jsonValue = objectMapper.writeValueAsString(new String[]{"Test, Kafka!", ""});
        } catch (Exception e) {
            throw new RuntimeException("Error converting to JSON", e);
        }

        // Send message
        ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key", jsonValue);
        try {
            producer.send(record);
            System.out.println("Message sent: " + jsonValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
