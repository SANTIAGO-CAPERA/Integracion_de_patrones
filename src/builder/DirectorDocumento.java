package builder;

import java.util.Arrays;
import java.util.List;

public class DirectorDocumento {
    public EstructuraDocumento construirReporteEjecutivo(DocumentBuilder builder) {
        List<String> columnas = Arrays.asList("Concepto", "Valor");
        List<List<String>> filas = Arrays.asList(
                Arrays.asList("Ingresos", "1000"),
                Arrays.asList("Egresos", "400")
        );
        return builder
                .addHeader("Resumen financiero trimestral")
                .addParagraph("Este reporte presenta los resultados del periodo.")
                .addParagraph("Fecha de emision: #{FECHA_ACTUAL}")
                .addParagraph("Pago con tarjeta 1234-5678-9012-3456 en documento confidencial")
                .addTable(columnas, filas)
                .addParagraph("Utilidad estimada: #{INGRESOS - EGRESOS}")
                .addFooter("Area de finanzas")
                .build();
    }

    public EstructuraDocumento construirFacturaSimple(DocumentBuilder builder) {
        List<String> columnas = Arrays.asList("Producto", "Cantidad", "Precio");
        List<List<String>> filas = Arrays.asList(
                Arrays.asList("Licencia", "2", "500")
        );
        return builder
                .addHeader("N 0001")
                .addTable(columnas, filas)
                .addParagraph("Total con IVA: #{PRECIO_BASE * 1.19 - DESCUENTO}")
                .addFooter("Gracias por su compra")
                .build();
    }
}
