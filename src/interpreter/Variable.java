package interpreter;


public class Variable extends TerminalExpression {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Object interpret(Context context) {
        return context.get(name);
    }
}