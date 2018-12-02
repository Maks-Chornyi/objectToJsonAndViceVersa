package singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class ObjectMapperSingleton {
    private static ObjectMapper objectMapper;

    private ObjectMapperSingleton() {
    }

    public static ObjectMapper getObjectMapper () {
        return Optional.ofNullable(objectMapper).orElse(objectMapper = new ObjectMapper());
    }
}
