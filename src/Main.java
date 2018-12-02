import com.fasterxml.jackson.core.JsonProcessingException;
import singleton.ObjectMapperSingleton;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Black","VolksWagen");
        String res = convertObjectToJSON(car);
        System.out.println(res);
        Car car1 = convertJSONToCarObject(res);
        System.out.println(car.hashCode() + " " + car1.hashCode());
    }

    private static Car convertJSONToCarObject(String json) {
        Car car = null;
        try {
            car = ObjectMapperSingleton.getObjectMapper().readValue(json, Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return car;
    }

    private static String convertObjectToJSON(Object obj) {
        String jsonFromObject = null;
        try {
            jsonFromObject = ObjectMapperSingleton.getObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonFromObject;
    }
}
