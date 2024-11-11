package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    private MapSchema mapSchema;
    private Map<String, String> emptyMap = new HashMap<>();
    private Map<String, String> testingMap = new HashMap<>(Map.of("key", "value"));

    @BeforeEach
    public void beforeEach() {
        Validator validator = new Validator();
        mapSchema = validator.map();
    }

    @Test
    public void mapNotRequired() {

        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(emptyMap));
        assertTrue(mapSchema.isValid(testingMap));
    }

    @Test
    public void mapRequired() {
        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(emptyMap));
        assertTrue(mapSchema.isValid(testingMap));

        mapSchema.required();

        assertTrue(mapSchema.isValid(emptyMap));
        assertTrue(mapSchema.isValid(testingMap));
        assertFalse(mapSchema.isValid(null));
    }

    @Test
    public void mapSizeOfTest() {
        mapSchema.required();

        assertTrue(mapSchema.sizeof(1).isValid(testingMap));
        assertFalse(mapSchema.isValid(emptyMap));
        assertFalse(mapSchema.sizeof(2).isValid(testingMap));

        testingMap.put("key1", "value1");

        assertTrue(mapSchema.isValid(testingMap));
    }
}
