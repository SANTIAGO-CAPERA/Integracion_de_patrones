package chain;

import interpreter.Context;
import interpreter.Expression;
import interpreter.ExpressionParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluadorExpresiones extends ProcesadorHandler {

    private static final Pattern MARCADOR = Pattern.compile("#\\{([^}]*)}");

    private Context context;

    public EvaluadorExpresiones(Context context) {
        this.context = context;
    }

    @Override
    protected ResultadoProceso hacerProceso(DocumentoEnProceso doc) {
        int cantidad = 0;

        for (int i = 0; i < doc.cantidad(); i++) {
            Matcher m = MARCADOR.matcher(doc.getContenido(i));
            StringBuilder sb = new StringBuilder();

            while (m.find()) {
                String formula = m.group(1).trim();
                try {
                    Expression e = ExpressionParser.parse(formula);
                    Object valor = e.interpret(context);
                    m.appendReplacement(sb, Matcher.quoteReplacement(String.valueOf(valor)));
                } catch (IllegalArgumentException ex) {

                    return ResultadoProceso.critico("error en #{" + formula + "}: " + ex.getMessage());
                }
                cantidad++;
            }
            m.appendTail(sb);
            doc.setContenido(i, sb.toString());
        }
        return ResultadoProceso.ok(cantidad + " formula(s) evaluada(s)");
    }
}

