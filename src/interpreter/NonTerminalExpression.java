package interpreter;

import java.math.BigDecimal;

//Combines two sub-expressions (binary operations).
public abstract class NonTerminalExpression implements Expression {

    protected final Expression left;
    protected final Expression right;

    protected NonTerminalExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    // Arithmetic operations expect numbers, so this method checks that
    protected BigDecimal asANumber(Object value) {
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        throw new IllegalArgumentException("A number was expected but got instead: " + value);
    }
}