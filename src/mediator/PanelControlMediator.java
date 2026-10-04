package mediator;

import builder.DirectorDocumento;
import builder.DocumentBuilder;
import builder.EstructuraDocumento;

public class PanelControlMediator implements DocumentEditorMediator {
    private final SelectorDeFormato selectorDeFormato;
    private final BarraDeHerramientasBuilder barraDeHerramientasBuilder;
    private final VistaPrevia vistaPrevia;
    private final BotonExportar botonExportar;

    private final DirectorDocumento director = new DirectorDocumento();
    private DocumentBuilder builderActual;
    private String tipoActual;
    private EstructuraDocumento documentoActual;

    // Se necesita implementacion de Bridge
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
            // Se necesita la integracion Bridge: motorActual
            actualizarPanel();
        } else if (TIPO_DOCUMENTO_CAMBIADO.equals(evento)) {
            tipoActual = barraDeHerramientasBuilder.getTipoDocumento();
            builderActual = crearBuilder(tipoActual);
            actualizarPanel();
        } else if (EXPORTAR_SOLICITADO.equals(evento)) {
            exportar();
        }
    }

    private DocumentBuilder crearBuilder(String tipo) {
        if (BarraDeHerramientasBuilder.REPORTE_EJECUTIVO.equals(tipo)) {
            return new builder.ReporteEjecutivoBuilder();
        }
        if (BarraDeHerramientasBuilder.FACTURA_SIMPLE.equals(tipo)) {
            return new builder.FacturaSimpleBuilder();
        }
        throw new IllegalArgumentException("Tipo de documento sin builder asociado: " + tipo);
    }

    private void actualizarPanel() {
        vistaPrevia.refrescar(builderActual, motorActual);
        botonExportar.setHabilitado(builderActual != null && motorActual != null);
    }

    private void exportar() {
        documentoActual = construirDocumento();
        System.out.println("   [Mediator] Exportando con builder=" + documentoActual.getTitulo() + " (" + documentoActual.cantidadElementos() + " elementos), motor=" + motorActual);
        // Integracion Bridge
    }

    private EstructuraDocumento construirDocumento() {
        if (BarraDeHerramientasBuilder.REPORTE_EJECUTIVO.equals(tipoActual)) {
            return director.construirReporteEjecutivo(builderActual);
        }
        return director.construirFacturaSimple(builderActual);
    }

    public DocumentBuilder getBuilderActual() {
        return builderActual;
    }

    public String getTipoActual() {
        return tipoActual;
    }

    public EstructuraDocumento getDocumentoActual() {
        return documentoActual;
    }

    public String getMotorActual() {
        return motorActual;
    }
}