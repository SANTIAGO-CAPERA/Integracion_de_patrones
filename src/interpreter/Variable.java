package interpreter;

public class Variable extends ExpresionTerminal {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Object interpret(Context context) {
        return context.get(name);
    }
}
