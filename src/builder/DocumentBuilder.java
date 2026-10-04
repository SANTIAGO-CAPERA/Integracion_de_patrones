package builder;

import java.util.List;

public interface DocumentBuilder {
    DocumentBuilder addHeader(String texto);
    DocumentBuilder addParagraph(String texto);
    DocumentBuilder addTable(List<String> columnas, List<List<String>> filas);
    DocumentBuilder addFooter(String texto);
    EstructuraDocumento build();
}