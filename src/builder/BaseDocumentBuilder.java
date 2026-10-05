package builder;

import flyweight.GlyphFactory;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDocumentBuilder implements DocumentBuilder {
    protected String titulo;
    protected List<ElementoDocumento> elementos;
    protected final GlyphFactory fabrica;

    protected BaseDocumentBuilder(String titulo) {
        this(titulo, new GlyphFactory());
    }

    protected BaseDocumentBuilder(String titulo, GlyphFactory fabrica) {
        this.titulo = titulo;
        this.fabrica = fabrica;
        this.elementos = new ArrayList<>();
    }

    @Override
    public DocumentBuilder addHeader(String texto) {
        elementos.add(new Encabezado(texto, fabrica, elementos.size()));
        return this;
    }

    @Override
    public DocumentBuilder addParagraph(String texto) {
        elementos.add(new Parrafo(texto, fabrica, elementos.size()));
        return this;
    }

    @Override
    public DocumentBuilder addTable(List<String> columnas, List<List<String>> filas) {
        elementos.add(new Tabla(columnas, filas, fabrica, elementos.size()));
        return this;
    }

    @Override
    public DocumentBuilder addFooter(String texto) {
        elementos.add(new PiePagina(texto, fabrica, elementos.size()));
        return this;
    }

    @Override
    public EstructuraDocumento build() {
        EstructuraDocumento resultado = new EstructuraDocumento(titulo, elementos, fabrica);
        elementos = new ArrayList<>();
        return resultado;
    }
}
