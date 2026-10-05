package bridge;

import java.util.ArrayList;
import java.util.List;

public class TablaTexto {

    public static List<List<String>> parsear(String contenido) {
        List<List<String>> filas = new ArrayList<>();
        for (String linea : contenido.split("\n")) {
            List<String> celdas = new ArrayList<>();
            for (String celda : linea.split("\\|")) {
                celdas.add(celda.trim());
            }
            filas.add(celdas);
        }
        return filas;
    }
}
