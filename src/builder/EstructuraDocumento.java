package builder;

import flyweight.GlyphFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstructuraDocumento {
    private final String titulo;
    private final List<ElementoDocumento> elementos;
    private final GlyphFactory fabrica;

    public EstructuraDocumento(String titulo, List<ElementoDocumento> elementos, GlyphFactory fabrica) {
        this.titulo = titulo;
        this.elementos = new ArrayList<>(elementos);
        this.fabrica = fabrica;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<ElementoDocumento> getElementos() {
        return Collections.unmodifiableList(elementos);
    }

    public GlyphFactory getFabrica() {
        return fabrica;
    }

    public int cantidadElementos() {
        return elementos.size();
    }
}
