package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class StringSchemaTest {

    private StringSchema stringSchema;
    private final String testingString1 = "3rd project";
    private final String testingString2 = "hexlet";


    @BeforeEach
    public void beforeEach() {
        Validator validator = new Validator();
        stringSchema = validator.string();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"3rd project", "hexlet", ""})
    public void stringNotRequiredTest(String testingValue) {
        assertTrue(stringSchema.isValid(testingValue));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3rd project", "hexlet", "a"})
    public void stringRequiredPassTest(String testingValue) {
        stringSchema.required();

        assertTrue(stringSchema.isValid(testingValue));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    public void stringRequiredFailTest(String testingValue) {
        stringSchema.required();

        assertFalse(stringSchema.isValid(testingValue));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3rd project", "hexlet"})
    public void minLengthStringPassTest(String testingValue) {
        stringSchema.required().minLength(6);

        assertTrue(stringSchema.isValid(testingValue));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "hex", "hexle"})
    public void minLengthStringFailTest(String testingValue) {
        stringSchema.required().minLength(6);

        assertFalse(stringSchema.isValid(testingValue));
    }

    @Test
    public void containsSubstringPassTest() {
        stringSchema.required().contains("3rd");

        assertTrue(stringSchema.isValid(testingString1));
    }

    @Test
    public void containsSubstringFailTest() {
        stringSchema.required().contains("hax");

        assertFalse(stringSchema.isValid(testingString2));
    }

    @Test
    public void changeParamInOneChainTest() {
        stringSchema.required().minLength(8).contains("hex").minLength(3);

        assertTrue(stringSchema.isValid(testingString2));
    }
}
