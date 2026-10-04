package chain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidadorSintaxis extends ProcesadorHandler {

    private static final Pattern ETIQUETA = Pattern.compile("<(/?)([a-zA-Z][a-zA-Z0-9]*)>");

    @Override
    protected ResultadoProceso hacerProceso(DocumentoEnProceso doc) {
        for (int i = 0; i < doc.cantidad(); i++) {
            String error = revisar(doc.getContenido(i));
            if (error != null) {
                return ResultadoProceso.critico("Elemento " + (i + 1) + " (" + doc.getTipo(i) + "): " + error);
            }
        }
        return ResultadoProceso.ok("sintaxis correcta");
    }

    private String revisar(String texto) {
        Deque<String> pila = new ArrayDeque<>();
        Matcher m = ETIQUETA.matcher(texto);

        while (m.find()) {
            boolean esCierre = !m.group(1).isEmpty();
            String nombre = m.group(2);

            if (!esCierre) {
                pila.push(nombre);
            } else if (pila.isEmpty() || !pila.pop().equals(nombre)) {
                return "etiqueta de cierre inesperada </" + nombre + ">";
            }
        }
        if (!pila.isEmpty()) {
            return "etiqueta sin cerrar <" + pila.peek() + ">";
        }

        int pos = texto.indexOf("#{");
        while (pos != -1) {
            if (texto.indexOf("}", pos) == -1) {
                return "formula sin cerrar en la posicion " + pos;
            }
            pos = texto.indexOf("#{", pos + 2);
        }
        return null;
    }
}

