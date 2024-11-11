package hexlet.code;

public class NumberSchema extends BaseSchema<Integer>{

    private boolean positive;
    private Integer minBound = Integer.MIN_VALUE;
    private Integer maxBound = Integer.MAX_VALUE;

    public NumberSchema() {
        super();
        positive = false;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int minBound, int maxBound) {
        this.minBound = minBound;
        this.maxBound = maxBound;
        return this;
    }

    @Override
    boolean isRequired(Integer testedValue) {
        if (positive) {
            return testedValue != null
                    && isNumberPositive(testedValue)
                    && isNumberFitsInRange(testedValue);
        } else {
            return testedValue != null
                    && isNumberFitsInRange(testedValue);
        }
    }

    @Override
    boolean isNotRequired(Integer testedValue) {
        if (testedValue == null) {
            return true;
        }

        if (positive) {
            return isNumberPositive(testedValue)
                    && isNumberFitsInRange(testedValue);
        } else {
            return isNumberFitsInRange(testedValue);
        }
    }

    private boolean isNumberFitsInRange(Integer testedValue) {
        return testedValue > minBound
                && testedValue < maxBound;
    }

    private boolean isNumberPositive(Integer testedValue) {
        return testedValue > 0;
    }
}
