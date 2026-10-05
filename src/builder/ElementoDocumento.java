package builder;

import flyweight.GlyphLine;

public interface ElementoDocumento {
    String getTipo();
    String getContenido();
    GlyphLine getLinea();
}
