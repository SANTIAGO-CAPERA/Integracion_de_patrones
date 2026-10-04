package builder;

public class PiePagina implements ElementoDocumento {
    private final String texto;

    public PiePagina(String texto) {
        this.texto = texto;
    }

    @Override
    public String getTipo() {
        return "PIE_PAGINA";
    }

    @Override
    public String getContenido() {
        return texto;
    }
}