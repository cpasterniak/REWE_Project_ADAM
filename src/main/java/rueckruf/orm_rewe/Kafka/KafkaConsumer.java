package rueckruf.orm_rewe.Kafka;

import lombok.RequiredArgsConstructor;
import rueckruf.orm_rewe.Service.RueckrufServiceSQL;
import rueckruf.orm_rewe.entity.Rueckruf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final RueckrufServiceSQL serviceSQL;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @KafkaListener(topics = "person-topic", groupId = "group_id")
    public void consume(String message) {
        try {
            Rueckruf rueckruf = objectMapper.readValue(message, Rueckruf.class);
            System.out.println("Empfangene Person: " + rueckruf);
            serviceSQL.createRueckruf(rueckruf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

