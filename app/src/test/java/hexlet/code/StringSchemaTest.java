package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void stringNotRequiredTest() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(testingString1));
    }

    @Test
    public void stringRequiredTest() {
        stringSchema.required();

        assertTrue(stringSchema.isValid(testingString1));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
    }

    @Test
    public void minLengthStringTest() {
        assertTrue(stringSchema.minLength(6).isValid(testingString1));

        stringSchema.required();

        assertTrue(stringSchema.minLength(7).isValid(testingString1));
        assertFalse(stringSchema.isValid(testingString2));
        assertTrue(stringSchema.minLength(3).isValid(testingString2));
    }

    @Test
    public void containsSubstringTest() {
        assertTrue(stringSchema.contains("3rd").isValid(testingString1));

        stringSchema.required();

        assertTrue(stringSchema.isValid(testingString1));
        assertFalse(stringSchema.contains("4th").isValid(testingString1));
    }

    @Test
    public void changeParamInOneChainTest() {
        stringSchema.required();

        assertTrue(stringSchema.minLength(8).contains("hex").minLength(3).isValid(testingString2));
        assertFalse(stringSchema.contains("project").contains("app").isValid(testingString1));
    }
}
