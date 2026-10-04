package builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstructuraDocumento {
    private final String titulo;
    private final List<ElementoDocumento> elementos;

    public EstructuraDocumento(String titulo, List<ElementoDocumento> elementos) {
        this.titulo = titulo;
        this.elementos = new ArrayList<>(elementos);
    }

    public String getTitulo() {
        return titulo;
    }

    public List<ElementoDocumento> getElementos() {
        return Collections.unmodifiableList(elementos);
    }

    public int cantidadElementos() {
        return elementos.size();
    }
}