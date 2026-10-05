package flyweight;

import builder.DirectorDocumento;
import builder.ElementoDocumento;
import builder.EstructuraDocumento;
import builder.FacturaSimpleBuilder;
import builder.ReporteEjecutivoBuilder;

public class FlyweightDemo {

    public static void main(String[] args) {

        GlyphFactory factory = new GlyphFactory();
        DirectorDocumento director = new DirectorDocumento();

        EstructuraDocumento reporte = director.construirReporteEjecutivo(new ReporteEjecutivoBuilder(factory));
        imprimir(reporte, factory);

        EstructuraDocumento factura = director.construirFacturaSimple(new FacturaSimpleBuilder(factory));
        imprimir(factura, factory);

        GlyphLine linea = new GlyphLine(factory);
        linea.addIcon("logo", "icons/logo.png", 10, 10, "#000000", 2);
        linea.addIcon("logo", "icons/logo.png", 50, 10, "#FF0000", 1);
        System.out.println("Mismo flyweight para ambos logos: "
                + (linea.getGlyphs().get(0).getGlyph() == linea.getGlyphs().get(1).getGlyph()));
        System.out.println(linea.getGlyphs().get(0).draw());
        System.out.println(linea.getGlyphs().get(1).draw());
    }

    private static void imprimir(EstructuraDocumento doc, GlyphFactory factory) {
        int glifos = 0;
        System.out.println("== " + doc.getTitulo() + " ==");
        for (ElementoDocumento e : doc.getElementos()) {
            System.out.println(e.getTipo() + ": " + e.getContenido());
            glifos += e.getLinea().size();
        }
        System.out.println("Glifos colocados: " + glifos);
        System.out.println("Solicitudes a la fabrica: " + factory.getTotalRequests());
        System.out.println("Flyweights creados (pool): " + factory.getPoolSize());
        System.out.println("Reutilizados: " + factory.getReusedCount());
        System.out.println();
    }
}