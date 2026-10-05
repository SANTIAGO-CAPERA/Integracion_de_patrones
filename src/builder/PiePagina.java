package builder;

import flyweight.GlyphFactory;
import flyweight.GlyphLine;

public class PiePagina implements ElementoDocumento {
    private final String texto;
    private final GlyphLine linea;

    public PiePagina(String texto, GlyphFactory fabrica, int fila) {
        this.texto = texto;
        this.linea = FabricaLineas.crear(fabrica, "PIE_PAGINA", texto, fila);
    }

    @Override
    public String getTipo() {
        return "PIE_PAGINA";
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
