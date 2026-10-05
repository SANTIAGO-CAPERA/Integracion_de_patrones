package builder;

import flyweight.GlyphFactory;
import flyweight.GlyphLine;

public class Encabezado implements ElementoDocumento {
    private final String texto;
    private final GlyphLine linea;

    public Encabezado(String texto, GlyphFactory fabrica, int fila) {
        this.texto = texto;
        this.linea = FabricaLineas.crear(fabrica, "ENCABEZADO", texto, fila);
    }

    @Override
    public String getTipo() {
        return "ENCABEZADO";
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
