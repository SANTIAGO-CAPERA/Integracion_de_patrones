package interpreter;

import java.math.BigDecimal;

public class Number extends ExpresionTerminal {

    private final BigDecimal value;

    public Number(String value) {
        this.value = new BigDecimal(value);
    }

    @Override
    public Object interpret(Context context) {
        return value;
    }
}
