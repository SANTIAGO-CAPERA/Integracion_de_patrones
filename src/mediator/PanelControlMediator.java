package mediator;

import bridge.Documento;
import bridge.DocumentoContinuo;
import bridge.DocumentoPaginado;
import bridge.HtmlRenderEngine;
import bridge.MarkdownRenderEngine;
import bridge.PdfRenderEngine;
import bridge.RenderizadorEngine;
import builder.DirectorDocumento;
import builder.DocumentBuilder;
import builder.EstructuraDocumento;
import builder.FacturaSimpleBuilder;
import builder.ReporteEjecutivoBuilder;
import chain.DocumentoEnProceso;

public class PanelControlMediator implements DocumentEditorMediator {
    private static final int ELEMENTOS_POR_PAGINA = 2;

    private final SelectorDeFormato selectorDeFormato;
    private final BarraDeHerramientasBuilder barraDeHerramientasBuilder;
    private final VistaPrevia vistaPrevia;
    private final BotonExportar botonExportar;

    private final DirectorDocumento director = new DirectorDocumento();
    private DocumentBuilder builderActual;
    private String tipoActual;

    private RenderizadorEngine motorActual;
    private Documento documentoActual;
    private String ultimaSalida;

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
            motorActual = crearMotor(selectorDeFormato.getFormato());
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
            return new ReporteEjecutivoBuilder();
        }
        if (BarraDeHerramientasBuilder.FACTURA_SIMPLE.equals(tipo)) {
            return new FacturaSimpleBuilder();
        }
        throw new IllegalArgumentException("Tipo de documento sin builder asociado: " + tipo);
    }

    private RenderizadorEngine crearMotor(String formato) {
        if (SelectorDeFormato.PDF.equals(formato)) {
            return new PdfRenderEngine();
        }
        if (SelectorDeFormato.HTML.equals(formato)) {
            return new HtmlRenderEngine();
        }
        if (SelectorDeFormato.MARKDOWN.equals(formato)) {
            return new MarkdownRenderEngine();
        }
        throw new IllegalArgumentException("Formato sin motor de render asociado: " + formato);
    }

    private void actualizarPanel() {
        String formato = motorActual == null ? null : motorActual.getNombre();
        vistaPrevia.refrescar(tipoActual, formato);
        botonExportar.setHabilitado(builderActual != null && motorActual != null);
    }

    private void exportar() {
        EstructuraDocumento estructura = construirEstructura();
        // Se necesita la integracion Chain: pasar el DocumentoEnProceso por la cadena de procesadores antes de renderizar.
        DocumentoEnProceso contenido = new DocumentoEnProceso(estructura);

        documentoActual = crearDocumento(contenido);
        ultimaSalida = documentoActual.renderizar();

        System.out.println("   [Mediator] " + estructura.getTitulo() + " renderizado con " + motorActual.getNombre()
                + " (" + documentoActual.getClass().getSimpleName() + "):");
        System.out.println(ultimaSalida);
    }

    private EstructuraDocumento construirEstructura() {
        if (BarraDeHerramientasBuilder.REPORTE_EJECUTIVO.equals(tipoActual)) {
            return director.construirReporteEjecutivo(builderActual);
        }
        return director.construirFacturaSimple(builderActual);
    }

    private Documento crearDocumento(DocumentoEnProceso contenido) {
        if (SelectorDeFormato.PDF.equals(motorActual.getNombre())) {
            return new DocumentoPaginado(contenido, motorActual, ELEMENTOS_POR_PAGINA);
        }
        return new DocumentoContinuo(contenido, motorActual);
    }

    public DocumentBuilder getBuilderActual() {
        return builderActual;
    }

    public String getTipoActual() {
        return tipoActual;
    }

    public RenderizadorEngine getMotorActual() {
        return motorActual;
    }

    public Documento getDocumentoActual() {
        return documentoActual;
    }

    public String getUltimaSalida() {
        return ultimaSalida;
    }
}