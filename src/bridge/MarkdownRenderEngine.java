package bridge;

import java.util.List;

public class MarkdownRenderEngine implements RenderizadorEngine {

    @Override
    public String getNombre() {
        return "MARKDOWN";
    }

    @Override
    public String iniciar(String titulo) {
        return "";
    }

    @Override
    public String renderEncabezado(String texto) {
        return "# " + texto + "\n\n";
    }

    @Override
    public String renderParrafo(String texto) {
        return texto + "\n\n";
    }

    @Override
    public String renderTabla(List<List<String>> filas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filas.size(); i++) {
            sb.append("| ").append(String.join(" | ", filas.get(i))).append(" |\n");
            if (i == 0) {
                sb.append("|");
                for (int c = 0; c < filas.get(0).size(); c++) {
                    sb.append(" --- |");
                }
                sb.append("\n");
            }
        }
        return sb.append("\n").toString();
    }

    @Override
    public String renderPie(String texto) {
        return "_" + texto + "_\n";
    }

    @Override
    public String saltoDePagina(int numero) {
        return "---\n\n";
    }

    @Override
    public String finalizar() {
        return "";
    }
}
