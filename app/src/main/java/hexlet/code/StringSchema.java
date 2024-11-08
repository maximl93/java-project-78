package hexlet.code;

public class StringSchema {

    private String containedSubstring;
    private int minStringLength;
    private boolean requiredToBeStringInstance;

    public StringSchema() {
        containedSubstring = "";
        minStringLength = 0;
        requiredToBeStringInstance = false;
    }

    public void required() {
        this.requiredToBeStringInstance = true;
    }

    public StringSchema minLength(int minStringLength) {
        this.minStringLength = minStringLength;
        return this;
    }

    public StringSchema contains(String checkSubstring) {
        this.containedSubstring = checkSubstring;
        return this;
    }

    public boolean isValid(String checkString) {
        if (requiredToBeStringInstance) {
            if (checkString != null
                && checkString.length() > minStringLength
                && checkString.contains(containedSubstring)) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }
}
