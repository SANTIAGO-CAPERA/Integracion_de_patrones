package builder;

import flyweight.GlyphFactory;
import flyweight.GlyphLine;
import java.util.ArrayList;
import java.util.List;

public class Tabla implements ElementoDocumento {
    private final List<String> columnas;
    private final List<List<String>> filas;
    private final GlyphLine linea;

    public Tabla(List<String> columnas, List<List<String>> filas, GlyphFactory fabrica, int fila) {
        this.columnas = new ArrayList<>(columnas);
        this.filas = new ArrayList<>();
        for (List<String> f : filas) {
            this.filas.add(new ArrayList<>(f));
        }
        this.linea = FabricaLineas.crear(fabrica, "TABLA", getContenido(), fila);
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

    @Override
    public GlyphLine getLinea() {
        return linea;
    }
}
