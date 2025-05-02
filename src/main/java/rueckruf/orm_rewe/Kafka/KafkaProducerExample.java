package rueckruf.orm_rewe.Kafka;
/**
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import rueckruf.orm_rewe.Service.RueckrufServiceAlt;
import rueckruf.orm_rewe.entity.Rueckruf;

@Service
public class KafkaProducerExample {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final rueckruf.orm_rewe.Service.RueckrufServiceAlt rueckrufService;
    private final ObjectMapper objectMapper;

    public KafkaProducerExample(KafkaTemplate<String, String> kafkaTemplate, RueckrufServiceAlt rueckrufService) {
        this.kafkaTemplate = kafkaTemplate;
        this.rueckrufService = rueckrufService;
        this.objectMapper = new ObjectMapper();
    }

    public void sendRueckrufById(Long id) {
        Rueckruf rueckruf = rueckrufService.getRueckrufById(id);
        if (rueckruf == null) {
            System.out.println("Rueckruf mit ID " + id + " nicht gefunden.");
            return;
        }

        try {
            System.out.println("Hello");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Wichtig für LocalDate
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String jsonMessage = objectMapper.writeValueAsString(rueckruf);
            kafkaTemplate.send("rueckruf-topic", jsonMessage);
            System.out.println("Rueckruf gesendet: " + jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
