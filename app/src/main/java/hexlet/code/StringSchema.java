package hexlet.code;

public class StringSchema extends BaseSchema<String>{

    private String substring;
    private int minLength;

    public StringSchema() {
        super();
        substring = "";
        minLength = 0;
        //required = false;
    }

    public StringSchema minLength(int minStringLength) {
        this.minLength = minStringLength;
        return this;
    }

    public StringSchema contains(String checkSubstring) {
        this.substring = checkSubstring;
        return this;
    }

    boolean isRequired(String testedString) {
        return testedString != null
                && testedString.length() > minLength
                && testedString.contains(substring);
    }

    boolean isNotRequired(String testedString) {
        if (testedString == null) {
            return true;
        }

        return testedString.length() >= minLength
                && testedString.contains(substring);
    }
}
