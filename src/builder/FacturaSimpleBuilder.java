package builder;

public class FacturaSimpleBuilder extends BaseDocumentBuilder {
    public FacturaSimpleBuilder() {
        super("Factura Simple");
    }

    @Override
    public DocumentBuilder addHeader(String texto) {
        return super.addHeader("Factura: " + texto);
    }
}