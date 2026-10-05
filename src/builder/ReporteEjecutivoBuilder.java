package builder;

import flyweight.GlyphFactory;

public class ReporteEjecutivoBuilder extends BaseDocumentBuilder {
    public ReporteEjecutivoBuilder() {
        super("Reporte Ejecutivo");
    }

    public ReporteEjecutivoBuilder(GlyphFactory fabrica) {
        super("Reporte Ejecutivo", fabrica);
    }

    @Override
    public DocumentBuilder addFooter(String texto) {
        return super.addFooter("Uso interno - " + texto);
    }
}
