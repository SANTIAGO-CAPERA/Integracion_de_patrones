package builder;

import java.util.ArrayList;
import java.util.List;

public class Tabla implements ElementoDocumento {
    private final List<String> columnas;
    private final List<List<String>> filas;

    public Tabla(List<String> columnas, List<List<String>> filas) {
        this.columnas = new ArrayList<>(columnas);
        this.filas = new ArrayList<>();
        for (List<String> fila : filas) {
            this.filas.add(new ArrayList<>(fila));
        }
    }

    public List<String> getColumnas() {
        return columnas;
    }

    public List<List<String>> getFilas() {
        return filas;
    }

    @Override
    public String getTipo() {
        return "TABLA";
    }

    @Override
    public String getContenido() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(" | ", columnas));
        for (List<String> fila : filas) {
            sb.append("\n").append(String.join(" | ", fila));
        }
        return sb.toString();
    }
}