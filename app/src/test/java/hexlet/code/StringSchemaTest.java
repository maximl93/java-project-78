package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringSchemaTest {

    private StringSchema stringSchema;
    private final String TESTING_STRING_1 = "3rd project";
    private final String TESTING_STRING_2 = "hexlet";


    @BeforeEach
    public void beforeEach() {
        Validator validator = new Validator();
        stringSchema = validator.string();
    }

    @Test
    public void stringNotRequiredTest() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(TESTING_STRING_1));
    }

    @Test
    public void stringRequiredTest() {
        stringSchema.required();

        assertTrue(stringSchema.isValid(TESTING_STRING_1));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
    }

    @Test
    public void minLengthStringTest() {
        assertTrue(stringSchema.minLength(6).isValid(TESTING_STRING_1));

        stringSchema.required();

        assertTrue(stringSchema.minLength(7).isValid(TESTING_STRING_1));
        assertFalse(stringSchema.isValid(TESTING_STRING_2));
        assertTrue(stringSchema.minLength(3).isValid(TESTING_STRING_2));
    }

    @Test
    public void containsSubstringTest() {
        assertTrue(stringSchema.contains("3rd").isValid(TESTING_STRING_1));

        stringSchema.required();

        assertTrue(stringSchema.isValid(TESTING_STRING_1));
        assertFalse(stringSchema.contains("4th").isValid(TESTING_STRING_1));
    }

    @Test
    public void changeParamInOneChainTest() {
        stringSchema.required();

        assertTrue(stringSchema.minLength(8).contains("hex").minLength(3).isValid(TESTING_STRING_2));
        assertFalse(stringSchema.contains("project").contains("app").isValid(TESTING_STRING_1));
    }
}
