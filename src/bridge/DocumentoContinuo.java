package bridge;

import chain.DocumentoEnProceso;

public class DocumentoContinuo extends Documento {

    public DocumentoContinuo(DocumentoEnProceso contenido, RenderizadorEngine motor) {
        super(contenido, motor);
    }

    @Override
    public String renderizar() {
        StringBuilder sb = new StringBuilder();
        sb.append(motor.iniciar(contenido.getTitulo()));
        for (int i = 0; i < contenido.cantidad(); i++) {
            sb.append(renderizarElemento(i));
        }
        sb.append(motor.finalizar());
        return sb.toString();
    }
}
