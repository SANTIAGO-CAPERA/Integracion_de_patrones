package chain;

import builder.ElementoDocumento;
import builder.EstructuraDocumento;
import builder.FabricaLineas;
import flyweight.GlyphFactory;
import flyweight.GlyphLine;
import java.util.ArrayList;
import java.util.List;

public class DocumentoEnProceso {

    private String titulo;
    private GlyphFactory fabrica;
    private List<String> tipos = new ArrayList<>();
    private List<String> contenidos = new ArrayList<>();
    private List<GlyphLine> lineas = new ArrayList<>();

    public DocumentoEnProceso(EstructuraDocumento original) {
        this.titulo = original.getTitulo();
        this.fabrica = original.getFabrica();
        for (ElementoDocumento e : original.getElementos()) {
            tipos.add(e.getTipo());
            contenidos.add(e.getContenido());
            lineas.add(e.getLinea());
        }
    }

    public String getTitulo() { return titulo; }

    public int cantidad() { return contenidos.size(); }

    public String getTipo(int i) { return tipos.get(i); }

    public String getContenido(int i) { return contenidos.get(i); }

    public GlyphLine getLinea(int i) { return lineas.get(i); }

    public GlyphFactory getFabrica() { return fabrica; }

    public int totalGlifos() {
        int total = 0;
        for (GlyphLine linea : lineas) {
            total += linea.size();
        }
        return total;
    }

    public void setContenido(int i, String nuevo) {
        if (nuevo.equals(contenidos.get(i))) {
            return;
        }
        contenidos.set(i, nuevo);
        lineas.set(i, FabricaLineas.crear(fabrica, tipos.get(i), nuevo, i));
    }
}
