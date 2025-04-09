package rueckruf.orm_rewe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;

import java.time.LocalDate;


public class OrmReweApplicationTests {

    @Test
    public void contextLoads() throws JsonProcessingException {
        Rueckruf rueckruf = new Rueckruf();
        rueckruf.setPrueFreigabe(LocalDate.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Wichtig für LocalDate
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Format: "yyyy-MM-dd"

        String json = objectMapper.writeValueAsString(rueckruf);
        System.out.println(json);
    }

}
