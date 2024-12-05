package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MapSchemaTest {

    private final Validator validator = new Validator();
    private MapSchema mapSchema;

    @BeforeEach
    public void beforeEach() {
        mapSchema = validator.map();
    }

    private static Stream<Map<String, Object>> testMapProvider() {
        return Stream.of(
                Map.of("Key", "value"),
                Map.of()
        );
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("testMapProvider")
    public void mapNotRequiredTest(Map<String, Object> testedMap) {
        assertTrue(mapSchema.isValid(testedMap));
    }

    @ParameterizedTest
    @MethodSource("testMapProvider")
    public void mapRequiredPassTest(Map<String, Object> testedMap) {
        mapSchema.required();

        assertTrue(mapSchema.isValid(testedMap));
    }

    @Test
    public void mapRequiredFailTest() {
        mapSchema.required();

        assertFalse(mapSchema.isValid(null));
    }

    @Test
    public void mapSizeOfPassTest() {
        Map<String, String> testingMap = new HashMap<>(Map.of("key", "value"));

        mapSchema.required().sizeof(1);

        assertTrue(mapSchema.isValid(testingMap));
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("testMapProvider")
    public void mapSizeOfFailTest(Map<String, Object> testedMap) {
        mapSchema.required().sizeof(2);

        assertFalse(mapSchema.isValid(testedMap));
    }

    private Map<String, BaseSchema<String>> stringSchemasProvider() {
        return Map.of("firstName", validator.string().required().contains("Maks"),
                "lastName", validator.string().required().minLength(5));
    }

    @Test
    public void shapeTestPassed1() {
        Map<String, String> testedMap = new HashMap<>();
        testedMap.put("firstName", "Maksim");
        testedMap.put("lastName", "Lukin");

        mapSchema.shape(stringSchemasProvider());

        assertTrue(mapSchema.isValid(testedMap));
    }

    @Test
    public void shapeTestFailed1() {
        Map<String, String> testedMap = new HashMap<>();
        testedMap.put("firstName", "Maxim");
        testedMap.put("lastName", "Luk");

        mapSchema.shape(stringSchemasProvider());

        assertFalse(mapSchema.isValid(testedMap));
    }

    private Map<String, BaseSchema<Integer>> numberSchemasProvider() {
        return Map.of("age", validator.number().required().range(10, 30),
                "year", validator.number().required().positive());
    }

    @Test
    public void shapeTestPassed2() {
        Map<String, Integer> testedMap = new HashMap<>();
        testedMap.put("age", 20);
        testedMap.put("year", 2000);

        mapSchema.shape(numberSchemasProvider());

        assertTrue(mapSchema.isValid(testedMap));
    }

    @Test
    public void shapeTestFailed2() {
        Map<String, Integer> testedMap = new HashMap<>();
        testedMap.put("age", 20);
        testedMap.put("year", -10);

        mapSchema.shape(numberSchemasProvider());

        assertFalse(mapSchema.isValid(testedMap));
    }
}
