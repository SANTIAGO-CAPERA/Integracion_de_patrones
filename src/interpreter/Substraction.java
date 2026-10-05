package interpreter;

public class Substraction extends ExpresionNoTerminal {

    public Substraction(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Object interpret(Context context) {
        return asANumber(left.interpret(context))
                .subtract(asANumber(right.interpret(context)));
    }
}
