package interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {

    private static final Pattern TOKEN = Pattern.compile("\\s*(\\d+(?:\\.\\d+)?|[A-Za-z_][A-Za-z0-9_]*|[-+*()])");

    private final List<String> tokens;
    private int pos;

    private ExpressionParser(List<String> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    public static Expression parse(String formula) {
        if (formula == null || formula.isBlank()) {
            throw new IllegalArgumentException("Empty formula");
        }
        ExpressionParser parser = new ExpressionParser(tokenizar(formula));
        Expression tree = parser.expresion();
        if (parser.pos < parser.tokens.size()) {
            throw new IllegalArgumentException("Unexpected token: " + parser.tokens.get(parser.pos));
        }
        return tree;
    }

    private static List<String> tokenizar(String formula) {
        List<String> tokens = new ArrayList<>();
        Matcher m = TOKEN.matcher(formula);
        int fin = 0;
        while (!formula.substring(fin).isBlank()) {
            m.region(fin, formula.length());
            if (!m.lookingAt()) {
                throw new IllegalArgumentException("Unexpected character in formula: " + formula.substring(fin).trim());
            }
            tokens.add(m.group(1));
            fin = m.end();
        }
        return tokens;
    }

    private String actual() {
        return pos < tokens.size() ? tokens.get(pos) : null;
    }

    private Expression expresion() {
        Expression tree = termino();
        while ("+".equals(actual()) || "-".equals(actual())) {
            String operador = tokens.get(pos++);
            Expression right = termino();
            tree = operador.equals("+") ? new Addition(tree, right) : new Substraction(tree, right);
        }
        return tree;
    }

    private Expression termino() {
        Expression tree = factor();
        while ("*".equals(actual())) {
            pos++;
            tree = new Multiplication(tree, factor());
        }
        return tree;
    }

    private Expression factor() {
        String token = actual();
        if (token == null) {
            throw new IllegalArgumentException("Missing operand in formula");
        }
        pos++;
        if (token.equals("(")) {
            Expression tree = expresion();
            if (!")".equals(actual())) {
                throw new IllegalArgumentException("Missing closing parenthesis");
            }
            pos++;
            return tree;
        }
        if (Character.isDigit(token.charAt(0))) {
            return new Number(token);
        }
        if (Character.isLetter(token.charAt(0)) || token.charAt(0) == '_') {
            return new Variable(token);
        }
        throw new IllegalArgumentException("Unexpected token: " + token);
    }
}
