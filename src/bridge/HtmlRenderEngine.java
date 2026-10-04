package bridge;

import java.util.List;

public class HtmlRenderEngine implements RenderizadorEngine {

    @Override
    public String getNombre() {
        return "HTML";
    }

    @Override
    public String iniciar(String titulo) {
        return "<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"UTF-8\">\n<title>" + titulo
                + "</title>\n</head>\n<body>\n";
    }

    @Override
    public String renderEncabezado(String texto) {
        return "<h1>" + texto + "</h1>\n";
    }

    @Override
    public String renderParrafo(String texto) {
        return "<p>" + texto + "</p>\n";
    }

    @Override
    public String renderTabla(List<List<String>> filas) {
        StringBuilder sb = new StringBuilder("<table border=\"1\">\n");
        for (int i = 0; i < filas.size(); i++) {
            String etiqueta = i == 0 ? "th" : "td";
            sb.append("  <tr>");
            for (String celda : filas.get(i)) {
                sb.append("<").append(etiqueta).append(">").append(celda)
                  .append("</").append(etiqueta).append(">");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    @Override
    public String renderPie(String texto) {
        return "<footer>" + texto + "</footer>\n";
    }

    @Override
    public String saltoDePagina(int numero) {
        return "<div style=\"page-break-after: always\"></div>\n";
    }

    @Override
    public String finalizar() {
        return "</body>\n</html>\n";
    }
}
