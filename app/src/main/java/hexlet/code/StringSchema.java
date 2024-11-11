package hexlet.code;

public class StringSchema extends BaseSchema<String>{

    private String substring;
    private int minLength;

    public StringSchema() {
        super();
        substring = "";
        minLength = 0;
    }

    public StringSchema minLength(int minStringLength) {
        this.minLength = minStringLength;
        return this;
    }

    public StringSchema contains(String checkSubstring) {
        this.substring = checkSubstring;
        return this;
    }

    @Override
    boolean isRequired(String testedValue) {
        return testedValue != null
                && testedValue.length() > minLength
                && testedValue.contains(substring);
    }

    @Override
    boolean isNotRequired(String testedValue) {
        if (testedValue == null) {
            return true;
        }

        return testedValue.length() >= minLength
                && testedValue.contains(substring);
    }
}
