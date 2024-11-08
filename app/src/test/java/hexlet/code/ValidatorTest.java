package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    Validator validator;
    StringSchema stringSchema;


    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        stringSchema = validator.string();
    }

    @Test
    public void stringNotRequiredTest() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
    }

    @Test
    public void stringRequiredTest() {
        stringSchema.required();

        assertTrue(stringSchema.isValid("3rd project"));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
    }

    @Test
    public void minLengthStringTest() {
        stringSchema.required();

        assertTrue(stringSchema.minLength(7).isValid("3rd project"));
        assertFalse(stringSchema.isValid("hexlet"));
        assertTrue(stringSchema.minLength(3).isValid("hexlet"));
    }

    @Test
    public void containsSubstringTest() {
        stringSchema.required();

        assertTrue(stringSchema.contains("3rd").isValid("3rd project"));
        assertFalse(stringSchema.contains("4th").isValid("3rd project"));
    }

    @Test
    public void changeParamInOneChainTest() {
        stringSchema.required();

        assertTrue(stringSchema.minLength(8).contains("hex").minLength(3).isValid("hexlet"));
        assertFalse(stringSchema.contains("project").contains("app").isValid("3rd project"));
    }
}
