package bridge;

import chain.DocumentoEnProceso;

public class DocumentoPaginado extends Documento {

    private final int elementosPorPagina;

    public DocumentoPaginado(DocumentoEnProceso contenido, RenderizadorEngine motor, int elementosPorPagina) {
        super(contenido, motor);
        if (elementosPorPagina < 1) {
            throw new IllegalArgumentException("Debe haber al menos un elemento por pagina");
        }
        this.elementosPorPagina = elementosPorPagina;
    }

    @Override
    public String renderizar() {
        StringBuilder sb = new StringBuilder();
        sb.append(motor.iniciar(contenido.getTitulo()));

        int pagina = 1;
        for (int i = 0; i < contenido.cantidad(); i++) {
            if (i > 0 && i % elementosPorPagina == 0) {
                pagina++;
                sb.append(motor.saltoDePagina(pagina));
            }
            sb.append(renderizarElemento(i));
        }

        sb.append(motor.finalizar());
        return sb.toString();
    }
}

