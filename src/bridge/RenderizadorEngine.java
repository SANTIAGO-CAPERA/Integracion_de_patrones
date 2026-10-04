package bridge;

import java.util.List;

public interface RenderizadorEngine {
    String getNombre();
    String iniciar(String titulo);
    String renderEncabezado(String texto);
    String renderParrafo(String texto);
    String renderTabla(List<List<String>> filas);
    String renderPie(String texto);
    String saltoDePagina(int numero);
    String finalizar();
}
