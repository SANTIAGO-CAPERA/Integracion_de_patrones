package interpreter;

public interface Expression {
    Object interpret(Context context);
}