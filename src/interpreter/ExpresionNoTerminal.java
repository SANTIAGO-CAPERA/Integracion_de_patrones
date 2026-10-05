package interpreter;

import java.math.BigDecimal;

public abstract class ExpresionNoTerminal implements Expression {

    protected final Expression left;
    protected final Expression right;

    protected ExpresionNoTerminal(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    protected BigDecimal asANumber(Object value) {
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        throw new IllegalArgumentException("A number was expected but got instead: " + value);
    }
}
