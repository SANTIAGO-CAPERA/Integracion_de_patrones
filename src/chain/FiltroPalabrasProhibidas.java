package chain;

import java.util.List;

public class FiltroPalabrasProhibidas extends ProcesadorHandler {

    private List<String> prohibidas;

    public FiltroPalabrasProhibidas(List<String> prohibidas) {
        this.prohibidas = prohibidas;
    }

    @Override
    protected ResultadoProceso hacerProceso(DocumentoEnProceso doc) {
        for (int i = 0; i < doc.cantidad(); i++) {
            String texto = doc.getContenido(i);

            for (String palabra : prohibidas) {
                texto = texto.replaceAll("(?i)" + palabra, "***");
            }

            texto = texto.replaceAll("\\b\\d{4}[- ]?\\d{4}[- ]?\\d{4}[- ]?\\d{4}\\b", "[DATO OCULTO]");

            doc.setContenido(i, texto);
        }
        return ResultadoProceso.ok("texto sanitizado");
    }
}
