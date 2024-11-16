package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map> {

    public MapSchema required() {
        addRule("required",
                Objects::nonNull);

        return this;
    }

    public MapSchema sizeof(int size) {
        addRule("sizeof",
                value -> value == null || value.size() >= size);

        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addRule("schemas",
                value -> schemas.entrySet().stream().allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<T> schema = entry.getValue();
                    if (value.containsKey(key)) {
                        return schema.isValid((T) value.get(key));
                    } else {
                        return true;
                    }
                }));

        return this;
    }
}
