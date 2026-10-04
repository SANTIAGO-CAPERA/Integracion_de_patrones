package builder;

public class Parrafo implements ElementoDocumento {
    private final String texto;

    public Parrafo(String texto) {
        this.texto = texto;
    }

    @Override
    public String getTipo() {
        return "PARRAFO";
    }

    @Override
    public String getContenido() {
        return texto;
    }
}