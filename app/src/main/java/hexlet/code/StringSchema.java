package hexlet.code;

import java.util.Optional;

public class StringSchema {

    private String containedSubstring;
    private int minStringLength;
    private boolean required;

    public StringSchema() {
        containedSubstring = "";
        minStringLength = 0;
        required = false;
    }

    public void required() {
        this.required = true;
    }

    public StringSchema minLength(int minStringLength) {
        this.minStringLength = minStringLength;
        return this;
    }

    public StringSchema contains(String checkSubstring) {
        this.containedSubstring = checkSubstring;
        return this;
    }

    public boolean isValid(String testedString) {
        Optional<String> testedValue = Optional.ofNullable(testedString);

        if (required) {
            return isRequired(testedValue.get());
        } else {
            return isNotRequired(testedValue.get());
        }
    }

    private boolean isRequired(String testedString) {
        return testedString != null
                && testedString.length() > minStringLength
                && testedString.contains(containedSubstring);
    }

    private boolean isNotRequired(String testedString) {
        return testedString.length() >= minStringLength
                && testedString.contains(containedSubstring);
    }
}
