package builder;

import flyweight.GlyphFactory;

public class FacturaSimpleBuilder extends BaseDocumentBuilder {
    public FacturaSimpleBuilder() {
        super("Factura Simple");
    }

    public FacturaSimpleBuilder(GlyphFactory fabrica) {
        super("Factura Simple", fabrica);
    }

    @Override
    public DocumentBuilder addHeader(String texto) {
        return super.addHeader("Factura: " + texto);
    }
}
