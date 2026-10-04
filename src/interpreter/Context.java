package interpreter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Saves the values of variables and functions.
public class Context {

    private final Map<String, Object> variables = new HashMap<>();

    public Context() {
        // Predefined variable
        variables.put("CURRENT_DATE", LocalDate.now());
    }

    // ─── Writing ───
    public Context define(String name, Object value) {
        // Numbers are saved as BigDecimals to avoid precision issues
        if (value instanceof Number) {
            value = new BigDecimal(value.toString());
        }
        variables.put(name, value);
        return this; // allows chaining
    }

    // ─── Reading ───
    public Object get(String name) {
        if (!variables.containsKey(name)) {
            throw new IllegalArgumentException("Undefined variable: " + name);
        }
        return variables.get(name);
    }
}