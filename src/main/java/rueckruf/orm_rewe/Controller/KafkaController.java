
package rueckruf.orm_rewe.Controller;

import org.springframework.web.bind.annotation.*;
import rueckruf.orm_rewe.Kafka.KafkaProducerExample;

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
