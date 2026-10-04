package bridge;

import builder.DirectorDocumento;
import builder.EstructuraDocumento;
import builder.FacturaSimpleBuilder;
import builder.ReporteEjecutivoBuilder;
import chain.DocumentoEnProceso;
import chain.EvaluadorExpresiones;
import chain.FiltroPalabrasProhibidas;
import chain.ProcesadorHandler;
import chain.ResultadoProceso;
import chain.ValidadorSintaxis;
import interpreter.Context;
import java.util.Arrays;

public class PruebaBridge {

    public static void main(String[] args) {
        DirectorDocumento director = new DirectorDocumento();

        Context contexto = new Context()
                .define("INGRESOS", 1000)
                .define("EGRESOS", 400)
                .define("PRECIO_BASE", 1000)
                .define("DESCUENTO", 50);

        ProcesadorHandler cadena = new ValidadorSintaxis();
        cadena.setSiguiente(new FiltroPalabrasProhibidas(Arrays.asList("confidencial")))
              .setSiguiente(new EvaluadorExpresiones(contexto));

        EstructuraDocumento reporte = director.construirReporteEjecutivo(new ReporteEjecutivoBuilder());
        DocumentoEnProceso reporteProcesado = new DocumentoEnProceso(reporte);
        ResultadoProceso resultadoReporte = cadena.procesar(reporteProcesado);

        if (resultadoReporte.isErrorCritico()) {
            System.out.println("No se renderiza: " + resultadoReporte.getMensaje());
            return;
        }

        Documento paginado = new DocumentoPaginado(reporteProcesado, new PdfRenderEngine(), 2);
        mostrar(paginado);

        paginado.setMotor(new HtmlRenderEngine());
        mostrar(paginado);

        Documento continuo = new DocumentoContinuo(reporteProcesado, new MarkdownRenderEngine());
        mostrar(continuo);

        EstructuraDocumento factura = director.construirFacturaSimple(new FacturaSimpleBuilder());
        DocumentoEnProceso facturaProcesada = new DocumentoEnProceso(factura);
        ResultadoProceso resultadoFactura = cadena.procesar(facturaProcesada);

        if (!resultadoFactura.isErrorCritico()) {
            mostrar(new DocumentoContinuo(facturaProcesada, new HtmlRenderEngine()));
        }
    }

    private static void mostrar(Documento documento) {
        System.out.println("===== " + documento.getClass().getSimpleName() + " -> "
                + documento.getMotor().getNombre() + " =====");
        System.out.println(documento.renderizar());
    }
}
