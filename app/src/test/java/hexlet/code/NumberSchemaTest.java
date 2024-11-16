package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberSchemaTest {

    private NumberSchema numberSchema;
    private final int testingPositive = 4;
    private final int testinNegative = -4;
    private final int minBound = 2;
    private final int maxBound = 10;

    @BeforeEach
    public void beforeEach() {
        Validator validator = new Validator();
        numberSchema = validator.number();
    }

    @Test
    public void numberNotRequiredTest() {
        assertTrue(numberSchema.isValid(testingPositive));
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.positive().isValid(testingPositive));
        assertTrue(numberSchema.isValid(null));
    }

    @Test
    public void numberRequiredTest() {
        numberSchema.required();

        assertTrue(numberSchema.isValid(testingPositive));
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    public void requiredPositiveNumberTest() {
        numberSchema.required();

        assertTrue(numberSchema.positive().isValid(testingPositive));
        assertFalse(numberSchema.isValid(testinNegative));
    }

    @Test
    public void numberFitsInRangeTest() {
        numberSchema.required();

        assertTrue(numberSchema.range(minBound, maxBound).isValid(testingPositive));
        assertFalse(numberSchema.isValid(testinNegative));
    }
}
