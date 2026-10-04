package builder;

public class Encabezado implements ElementoDocumento {
    private final String texto;

    public Encabezado(String texto) {
        this.texto = texto;
    }

    @Override
    public String getTipo() {
        return "ENCABEZADO";
    }

    @Override
    public String getContenido() {
        return texto;
    }
}