import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonWithAdditionalField = "{\"color\":\"White\",\"type\":\"Type\",\"year\":\"1999\"}";
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode jsonNodeRoot = null;
        try {
            jsonNodeRoot = objectMapper.readTree(jsonWithAdditionalField);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode jsonNodeYear = jsonNodeRoot.get("year");
        String year = jsonNodeYear.asText();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);


        Car car = new Car.Builder().color("blue").type("Cool").build();
        Car car1 = null;
        try {
            car1 = objectMapper.readValue(jsonWithAdditionalField,Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(car1);
    }
}
