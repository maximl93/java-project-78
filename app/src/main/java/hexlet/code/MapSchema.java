package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>>{

    private int sizeOfMap;

    public MapSchema() {
        super();
        this.sizeOfMap = 0;
    }

    @Override
    boolean isRequired(Map<?, ?> testedValue) {
        return testedValue != null && isMapExactSize(testedValue);
    }

    @Override
    boolean isNotRequired(Map<?, ?> testedValue) {
        if (testedValue == null) {
            return true;
        }

        return isMapExactSize(testedValue);
    }

    public MapSchema sizeof(int newSize) {
        this.sizeOfMap = newSize;
        return this;
    }

    private boolean isMapExactSize(Map<?, ?> testedValue) {
        return testedValue.size() >= sizeOfMap;
    }
}
