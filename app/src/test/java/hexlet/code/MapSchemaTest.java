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

    private Validator validator = new Validator();
    private MapSchema mapSchema;
    private Map<String, String> emptyMap = new HashMap<>();
    private Map<String, String> testingMap = new HashMap<>(Map.of("key", "value"));

    @BeforeEach
    public void beforeEach() {
        mapSchema = validator.map();
    }

    @Test
    public void mapNotRequiredTest() {
        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(emptyMap));
        assertTrue(mapSchema.isValid(testingMap));
    }

    @Test
    public void mapRequiredTest() {
        mapSchema.required();

        assertTrue(mapSchema.isValid(emptyMap));
        assertTrue(mapSchema.isValid(testingMap));
        assertFalse(mapSchema.isValid(null));
    }

    @Test
    public void mapSizeOfTest1() {
        mapSchema.required().sizeof(1);

        assertTrue(mapSchema.isValid(testingMap));
        assertFalse(mapSchema.isValid(emptyMap));
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
    public void mapSizeOfTest2(Map<String, Object> testedMap) {
        mapSchema.required().sizeof(2);

        assertFalse(mapSchema.isValid(testedMap));
    }

    @Test
    public void shapeTestPassed1() {
        Map<String, String> testedMap = new HashMap<>();
        testedMap.put("firstName", "Maksim");
        testedMap.put("lastName", "Lukin");
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);

        assertTrue(mapSchema.isValid(testedMap));
    }

    @Test
    public void shapeTestPassed2() {
        Map<String, Integer> testedMap = new HashMap<>();
        testedMap.put("age", 20);
        testedMap.put("year", 2000);
        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put("age", validator.number().required().range(10, 30));
        schemas.put("year", validator.number().required().positive());

        mapSchema.shape(schemas);

        assertTrue(mapSchema.isValid(testedMap));
    }

    @Test
    public void shapeTestFailed1() {
        Map<String, String> testedMap = new HashMap<>();
        testedMap.put("firstName", "Maksim");
        testedMap.put("lastName", null);
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required());

        mapSchema.shape(schemas);

        assertFalse(mapSchema.isValid(testedMap));
    }

    @Test
    public void shapeTestFailed2() {
        Map<String, Integer> testedMap = new HashMap<>();
        testedMap.put("age", 20);
        testedMap.put("year", -10);
        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put("age", validator.number().required().range(10, 30));
        schemas.put("year", validator.number().required().positive());

        mapSchema.shape(schemas);

        assertFalse(mapSchema.isValid(testedMap));
    }
}
