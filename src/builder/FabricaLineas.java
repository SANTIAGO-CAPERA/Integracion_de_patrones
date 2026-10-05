package builder;

import flyweight.GlyphFactory;
import flyweight.GlyphLine;

public class FabricaLineas {
    private static final int MARGEN_X = 10;
    private static final int ALTO_FILA = 20;

    private FabricaLineas() {
    }

    public static GlyphLine crear(GlyphFactory fabrica, String tipo, String texto, int fila) {
        GlyphLine linea = new GlyphLine(fabrica);
        int y = 10 + fila * ALTO_FILA;
        String limpio = texto.replace('\n', ' ');
        switch (tipo) {
            case "ENCABEZADO":
                linea.addIcon("logo", "icons/logo.png", MARGEN_X, y, "#000000", 2);
                linea.addText(limpio, "Arial", MARGEN_X + 24, y, "#000000", 2);
                break;
            case "TABLA":
                linea.addText(limpio, "Courier", MARGEN_X, y, "#000000", 1);
                break;
            case "PIE_PAGINA":
                linea.addText(limpio, "Arial", MARGEN_X, y, "#666666", 1);
                break;
            default:
                linea.addText(limpio, "Arial", MARGEN_X, y, "#333333", 1);
                break;
        }
        return linea;
    }
}
