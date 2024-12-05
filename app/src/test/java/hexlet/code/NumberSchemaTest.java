package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberSchemaTest {

    private NumberSchema numberSchema;
    private final int testingPositive = 4;
    private final int testingNegative = -4;
    private final int minBound = 2;
    private final int maxBound = 10;

    @BeforeEach
    public void beforeEach() {
        Validator validator = new Validator();
        numberSchema = validator.number();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {4, -4})
    public void numberNotRequiredTest(Integer testingValue) {
        assertTrue(numberSchema.isValid(testingValue));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, -4})
    public void numberRequiredPassTest(Integer testingValue) {
        numberSchema.required();

        assertTrue(numberSchema.isValid(testingValue));
    }

    @Test
    public void numberRequiredFailTest() {
        numberSchema.required();

        assertFalse(numberSchema.isValid(null));
    }

    @Test
    public void requiredPositiveNumberPassTest() {
        numberSchema.required().positive();

        assertTrue(numberSchema.isValid(testingPositive));
    }

    @Test
    public void requiredPositiveNumberFailTest() {
        numberSchema.required().positive();

        assertFalse(numberSchema.isValid(testingNegative));
    }


    @Test
    public void numberFitsInRangePassTest() {
        numberSchema.required().range(minBound, maxBound);

        assertTrue(numberSchema.isValid(testingPositive));
    }

    @Test
    public void numberFitsInRangeFailTest() {
        numberSchema.required().range(minBound, maxBound);

        assertFalse(numberSchema.isValid(testingNegative));
    }
}
