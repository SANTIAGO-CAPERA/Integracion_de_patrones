package interpreter;


public class Multiplication extends NonTerminalExpression {

    public Multiplication(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Object interpret(Context context) {
        return asANumber(left.interpret(context))
                .multiply(asANumber(right.interpret(context)));
    }
}