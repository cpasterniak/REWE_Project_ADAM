package rueckruf.orm_rewe;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/socket") // Empfängt Nachrichten an "/app/rueckruf"
    @SendTo("/topic/updates") // Sendet Antwort an alle Subscriber von "/topic/updates"
    public String handleRueckruf(String message) {
        System.out.println("Empfangene Nachricht: " + message);
        return "Neuer Rueckruf: " + message; // Beispielantwort
    }
}
