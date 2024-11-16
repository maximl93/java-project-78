package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addRule("required",
                value -> value != null && !value.isEmpty());

        return this;
    }

    public StringSchema contains(String substring) {
        addRule("contains",
                value -> (value == null || value.isEmpty()) || value.contains(substring));

        return this;
    }


    public StringSchema minLength(int minStringLength) {
        addRule("minLength",
                value -> (value == null || value.isEmpty()) || value.length() >= minStringLength);

        return this;
    }
}
