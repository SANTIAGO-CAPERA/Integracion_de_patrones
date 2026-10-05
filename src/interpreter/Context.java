package interpreter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Context {

    private final Map<String, Object> variables = new HashMap<>();

    public Context() {
        variables.put("CURRENT_DATE", LocalDate.now());
        variables.put("FECHA_ACTUAL", LocalDate.now());
    }

    public Context define(String name, Object value) {
        if (value instanceof java.lang.Number) {
            value = new BigDecimal(value.toString());
        }
        variables.put(name, value);
        return this;
    }

    public Object get(String name) {
        if (!variables.containsKey(name)) {
            throw new IllegalArgumentException("Undefined variable: " + name);
        }
        return variables.get(name);
    }
}
