package interpreter;


public class Addition extends NonTerminalExpression {

    public Addition(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Object interpret(Context context) {
        return asANumber(left.interpret(context))
                .add(asANumber(right.interpret(context)));
    }
}