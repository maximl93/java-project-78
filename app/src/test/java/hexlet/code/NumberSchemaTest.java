package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    private NumberSchema numberSchema;
    private final int TESTING_POSITIVE = 4;
    private final int TESTING_NEGATIVE = -4;
    private final int MIN_BOUND = 2;
    private final int MAX_BOUND = 10;

    @BeforeEach
    public void beforeEach() {
        Validator validator = new Validator();
        numberSchema = validator.number();
    }

   @Test
   public void numberNotRequiredTest() {
       assertTrue(numberSchema.isValid(TESTING_POSITIVE));
       assertTrue(numberSchema.isValid(null));
       assertTrue(numberSchema.positive().isValid(TESTING_POSITIVE));
       assertTrue(numberSchema.isValid(null));
   }

    @Test
    public void numberRequiredTest() {
        numberSchema.required();

        assertTrue(numberSchema.isValid(TESTING_POSITIVE));
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    public void requiredPositiveNumberTest() {
        numberSchema.required();

        assertTrue(numberSchema.positive().isValid(TESTING_POSITIVE));
        assertFalse(numberSchema.isValid(TESTING_NEGATIVE));
    }

    @Test
    public void numberFitsInRangeTest() {
        numberSchema.required();

        assertTrue(numberSchema.range(MIN_BOUND, MAX_BOUND).isValid(TESTING_POSITIVE));
        assertFalse(numberSchema.isValid(TESTING_NEGATIVE));
    }
}
