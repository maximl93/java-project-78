package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class MapSchema extends BaseSchema<Map<?, ?>>{

    private int sizeOfMap;
    private Map<String, BaseSchema<?>> schemas;

    public MapSchema() {
        super();
        this.sizeOfMap = 0;
        schemas = new HashMap<>();
    }

    @Override
    boolean isRequired(Map<?, ?> testedValue) {
        return testedValue != null && isMapTheExactSize(testedValue);
    }

    @Override
    boolean isNotRequired(Map<?, ?> testedValue) {
        if (testedValue == null) {
            return true;
        }

        return isMapTheExactSize(testedValue);
    }

    public MapSchema sizeof(int newSize) {
        this.sizeOfMap = newSize;
        return this;
    }

    private boolean isMapTheExactSize(Map<?, ?> testedValue) {
        return testedValue.size() >= sizeOfMap;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.schemas.putAll(schemas);
        return this;
    }
    /*
    не понимаю как произвести проверку значений мапы согласно настроенной схемы
     */
    private boolean isPassedAllConditions(Map<?, ?> testedValue) {
        AtomicBoolean passed = new AtomicBoolean(true);

        testedValue.forEach((key, value) -> {
            BaseSchema<?> temp = schemas.get(key);
            if (!temp.isValid(value)) {
                passed.set(false);
            }
        });

        return passed.get();
    }
}
