package bridge;

import chain.DocumentoEnProceso;
import java.util.List;

public abstract class Documento {

    protected final DocumentoEnProceso contenido;
    protected RenderizadorEngine motor;

    protected Documento(DocumentoEnProceso contenido, RenderizadorEngine motor) {
        this.contenido = contenido;
        this.motor = motor;
    }

    public abstract String renderizar();

    public void setMotor(RenderizadorEngine motor) {
        this.motor = motor;
    }

    public RenderizadorEngine getMotor() {
        return motor;
    }

    protected String renderizarElemento(int i) {
        String tipo = contenido.getTipo(i);
        String texto = contenido.getContenido(i);

        switch (tipo) {
            case "ENCABEZADO":
                return motor.renderEncabezado(texto);
            case "PARRAFO":
                return motor.renderParrafo(texto);
            case "TABLA":
                List<List<String>> filas = TablaTexto.parsear(texto);
                return motor.renderTabla(filas);
            case "PIE_PAGINA":
                return motor.renderPie(texto);
            default:
                throw new IllegalArgumentException("Tipo de elemento desconocido: " + tipo);
        }
    }
}
