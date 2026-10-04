package mediator;

public class PanelControlMediator implements DocumentEditorMediator {
    private final SelectorDeFormato selectorDeFormato;
    private final BarraDeHerramientasBuilder barraDeHerramientasBuilder;
    private final VistaPrevia vistaPrevia;
    private final BotonExportar botonExportar;

    // Se necesitan las clases DocumentBuilder y RenderEngine para poder construir y renderizar el documento final.
    private String builderActual;
    private String motorActual;

    public PanelControlMediator(SelectorDeFormato selectorDeFormato, BarraDeHerramientasBuilder barraDeHerramientasBuilder, VistaPrevia vistaPrevia, BotonExportar botonExportar) {
        this.selectorDeFormato = selectorDeFormato;
        this.barraDeHerramientasBuilder = barraDeHerramientasBuilder;
        this.vistaPrevia = vistaPrevia;
        this.botonExportar = botonExportar;

        this.selectorDeFormato.setMediator(this);
        this.barraDeHerramientasBuilder.setMediator(this);
        this.vistaPrevia.setMediator(this);
        this.botonExportar.setMediator(this);
    }

    public void notificar(ComponenteUI emisor, String evento) {
        System.out.println("   [Mediator] " + emisor.getNombre() + " -> " + evento);

        if (FORMATO_CAMBIADO.equals(evento)) {
            motorActual = selectorDeFormato.getFormato();
            // Se necesita la integracion Bridge: motorActual = crear PdfRenderEngine / HtmlRenderEngine / MarkdownRenderEngine
            actualizarPanel();
        } else if (TIPO_DOCUMENTO_CAMBIADO.equals(evento)) {
            builderActual = barraDeHerramientasBuilder.getTipoDocumento();
            // Se necesita la integracion Builder: builderActual = crear ReporteEjecutivoBuilder / FacturaSimpleBuilder
            actualizarPanel();
        } else if (EXPORTAR_SOLICITADO.equals(evento)) {
            exportar();
        }
    }

    private void actualizarPanel() {
        vistaPrevia.refrescar(builderActual, motorActual);
        botonExportar.setHabilitado(builderActual != null && motorActual != null);
    }

    private void exportar() {
        // Se necesita la integracion: builder.build() -> cadena de procesadores -> documento.renderizar()
        System.out.println("   [Mediator] Exportando con builder=" + builderActual + " y motor=" + motorActual);
    }

    public String getBuilderActual() {
        return builderActual;
    }

    public String getMotorActual() {
        return motorActual;
    }
}