package chain;

import builder.ElementoDocumento;
import builder.EstructuraDocumento;
import java.util.ArrayList;
import java.util.List;


public class DocumentoEnProceso {

    private String titulo;
    private List<String> tipos = new ArrayList<>();
    private List<String> contenidos = new ArrayList<>();

    public DocumentoEnProceso(EstructuraDocumento original) {
        this.titulo = original.getTitulo();
        for (ElementoDocumento e : original.getElementos()) {
            tipos.add(e.getTipo());
            contenidos.add(e.getContenido());
        }
    }

    public String getTitulo() { return titulo; }

    public int cantidad() { return contenidos.size(); }

    public String getTipo(int i) { return tipos.get(i); }

    public String getContenido(int i) { return contenidos.get(i); }

    public void setContenido(int i, String nuevo) { contenidos.set(i, nuevo); }
}

