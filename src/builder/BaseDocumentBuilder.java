package builder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDocumentBuilder implements DocumentBuilder {
    protected String titulo;
    protected List<ElementoDocumento> elementos;

    protected BaseDocumentBuilder(String titulo) {
        this.titulo = titulo;
        this.elementos = new ArrayList<>();
    }

    @Override
    public DocumentBuilder addHeader(String texto) {
        elementos.add(new Encabezado(texto));
        return this;
    }

    @Override
    public DocumentBuilder addParagraph(String texto) {
        elementos.add(new Parrafo(texto));
        return this;
    }

    @Override
    public DocumentBuilder addTable(List<String> columnas, List<List<String>> filas) {
        elementos.add(new Tabla(columnas, filas));
        return this;
    }

    @Override
    public DocumentBuilder addFooter(String texto) {
        elementos.add(new PiePagina(texto));
        return this;
    }

    @Override
    public EstructuraDocumento build() {
        EstructuraDocumento resultado = new EstructuraDocumento(titulo, elementos);
        elementos = new ArrayList<>();
        return resultado;
    }
}