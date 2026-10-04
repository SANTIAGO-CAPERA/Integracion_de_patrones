package interpreter;

// ─── EXPRESSION PARSER ───
// Not part of the Interpreter pattern: it only turns the formula text into the expression tree.
// Rule: operands and operators are separated by spaces, e.g. "PRECIO_BASE * 1.19 - DESCUENTO".
// It is evaluated from left to right (no operator precedence, no parentheses).
public class ExpressionParser {

    public static Expression parse(String formula) {
        if (formula.isBlank()) {
            throw new IllegalArgumentException("Empty formula");
        }
        String[] tokens = formula.trim().split("\\s+");
        if (tokens.length % 2 == 0) {
            throw new IllegalArgumentException("Missing operand in formula: " + formula);
        }

        // ─── BUILD THE TREE ───
        // The tree built so far becomes the left child of the next operation
        Expression tree = operand(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            Expression right = operand(tokens[i + 1]);
            switch (tokens[i]) {
                case "+": tree = new Addition(tree, right); break;
                case "-": tree = new Substraction(tree, right); break;
                case "*": tree = new Multiplication(tree, right); break;
                default: throw new IllegalArgumentException("Unknown operator: " + tokens[i]);
            }
        }
        return tree;
    }

    // ─── OPERAND ───
    // Starts with a digit -> number; otherwise -> variable
    private static Expression operand(String token) {
        if (Character.isDigit(token.charAt(0))) {
            return new Number(token);
        }
        return new Variable(token);
    }
}