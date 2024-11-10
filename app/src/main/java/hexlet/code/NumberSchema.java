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

    boolean isRequired(Integer testedNumber) {
        if (positive) {
            return testedNumber != null
                    && isNumberPositive(testedNumber)
                    && isNumberFitsInRange(testedNumber);
        } else {
            return testedNumber != null
                    && isNumberFitsInRange(testedNumber);
        }
    }

    boolean isNotRequired(Integer testedNumber) {
        if (testedNumber == null) {
            return true;
        }

        if (positive) {
            return isNumberPositive(testedNumber)
                    && isNumberFitsInRange(testedNumber);
        } else {
            return isNumberFitsInRange(testedNumber);
        }
    }

    private boolean isNumberFitsInRange(Integer testedNumber) {
        return testedNumber > minBound
                && testedNumber < maxBound;
    }

    private boolean isNumberPositive(Integer testedNumber) {
        return testedNumber > 0;
    }
}
