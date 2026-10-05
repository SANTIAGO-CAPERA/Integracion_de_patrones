package builder;

import flyweight.GlyphFactory;
import flyweight.GlyphLine;

public class Parrafo implements ElementoDocumento {
    private final String texto;
    private final GlyphLine linea;

    public Parrafo(String texto, GlyphFactory fabrica, int fila) {
        this.texto = texto;
        this.linea = FabricaLineas.crear(fabrica, "PARRAFO", texto, fila);
    }

    @Override
    public String getTipo() {
        return "PARRAFO";
    }

    @Override
    public String getContenido() {
        return texto;
    }

    @Override
    public GlyphLine getLinea() {
        return linea;
    }
}
