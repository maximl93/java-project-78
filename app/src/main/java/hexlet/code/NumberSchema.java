package hexlet.code;

import java.util.Optional;

public class NumberSchema {

    private boolean required;
    private boolean numberMustBePositive;
    private Integer minBound = Integer.MIN_VALUE;
    private Integer maxBound = Integer.MAX_VALUE;

    public NumberSchema() {
        required = false;
        numberMustBePositive = false;
    }

    public void required() {
        this.required = true;
    }

    public NumberSchema positive() {
        this.numberMustBePositive = true;
        return this;
    }

    public NumberSchema range(int minBound, int maxBound) {
        this.minBound = minBound;
        this.maxBound = maxBound;
        return this;
    }

    public boolean isValid(Integer testedNumber) {
        if (required) {
            return isRequired(testedNumber);
        } else {
            return isNotRequired(testedNumber);
        }
    }

    private boolean isRequired(Integer testedNumber) {
        if (numberMustBePositive) {
            return testedNumber != null
                    && isNumberPositive(testedNumber)
                    && isNumberFitsInRange(testedNumber);
        } else {
            return testedNumber != null
                    && isNumberFitsInRange(testedNumber);
        }
    }

    private boolean isNotRequired(Integer testedNumber) {
        if (testedNumber == null) {
            return true;
        }

        if (numberMustBePositive) {
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
