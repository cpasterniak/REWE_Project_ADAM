
package rueckruf.orm_rewe.Kafka;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaProducerExample kafkaProducerService;

    public KafkaController(KafkaProducerExample kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/send/{id}")
    public String sendRueckruf(@PathVariable Long id) {
        kafkaProducerService.sendRueckrufById(id);
        return "Rueckruf mit ID " + id + " gesendet!";
    }
}
