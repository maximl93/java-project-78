package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {


    private Map<String, Predicate<T>> schemeRules = new HashMap<>();

    public void addRule(String rule, Predicate<T> ruleLogic) {
        schemeRules.put(rule, ruleLogic);
    }

    public boolean isValid(T testedValue) {
        return schemeRules.values()
                .stream()
                .allMatch(predicate -> predicate.test(testedValue));
    }
}
