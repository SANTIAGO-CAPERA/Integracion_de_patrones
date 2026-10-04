package mediator;

import builder.DocumentBuilder;

public class VistaPrevia extends ComponenteUI {
    private String contenido;

    public VistaPrevia() {
        super("VistaPrevia");
        this.contenido = "(sin configuracion)";
    }

    public void refrescar(DocumentBuilder tipoDocumento, String formato) {
        String tipo = tipoDocumento == null ? "sin tipo" : tipoDocumento.toString();
        String fmt = formato == null ? "sin formato" : formato;
        this.contenido = "Documento " + tipo + " en " + fmt;
        System.out.println("[" + getNombre() + "] Refrescada -> " + contenido);
    }

    public String getContenido() {
        return contenido;
    }
}