package builder;

public class ReporteEjecutivoBuilder extends BaseDocumentBuilder {
    public ReporteEjecutivoBuilder() {
        super("Reporte Ejecutivo");
    }

    @Override
    public DocumentBuilder addFooter(String texto) {
        return super.addFooter("Confidencial - " + texto);
    }
}
