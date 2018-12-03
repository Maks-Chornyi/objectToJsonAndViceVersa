import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import jdk.internal.org.objectweb.asm.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        List<Car> carList = new ArrayList<>();
        carList.add(car);
        carList.add(car1);
        String jsonCarList = null;
        try {
            jsonCarList = objectMapper.writeValueAsString(carList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonCarList);

        objectMapper = new ObjectMapper();
        List<Car> listFromJson = null;

        try {
            //listFromJson = objectMapper.readValue(jsonCarList, new TypeReference<List<Car>>() {});
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, Car.class);
            listFromJson = objectMapper.readValue(jsonCarList, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(listFromJson.get(1));

    }
}
