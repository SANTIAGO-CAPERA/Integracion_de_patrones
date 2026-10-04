package builder;

public class PruebaBuilder {
    public static void main(String[] args) {
        DirectorDocumento director = new DirectorDocumento();

        EstructuraDocumento reporte = director.construirReporteEjecutivo(new ReporteEjecutivoBuilder());
        imprimir(reporte);

        EstructuraDocumento factura = director.construirFacturaSimple(new FacturaSimpleBuilder());
        imprimir(factura);
    }

    private static void imprimir(EstructuraDocumento documento) {
        System.out.println(documento.getTitulo() + " - elementos: " + documento.cantidadElementos());
        for (ElementoDocumento e : documento.getElementos()) {
            System.out.println(e.getTipo() + ": " + e.getContenido());
        }
        System.out.println();
    }
}