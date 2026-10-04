package bridge;

import java.util.List;

public class PdfRenderEngine implements RenderizadorEngine {

    @Override
    public String getNombre() {
        return "PDF";
    }

    @Override
    public String iniciar(String titulo) {
        return "%PDF-1.4 | " + titulo + "\n[ pagina 1 ]\n";
    }

    @Override
    public String renderEncabezado(String texto) {
        String linea = "=".repeat(texto.length());
        return texto.toUpperCase() + "\n" + linea + "\n";
    }

    @Override
    public String renderParrafo(String texto) {
        return texto + "\n";
    }

    @Override
    public String renderTabla(List<List<String>> filas) {
        int columnas = filas.get(0).size();
        int[] anchos = new int[columnas];
        for (List<String> fila : filas) {
            for (int c = 0; c < fila.size() && c < columnas; c++) {
                anchos[c] = Math.max(anchos[c], fila.get(c).length());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filas.size(); i++) {
            List<String> fila = filas.get(i);
            for (int c = 0; c < columnas; c++) {
                String celda = c < fila.size() ? fila.get(c) : "";
                sb.append(String.format("%-" + anchos[c] + "s", celda));
                if (c < columnas - 1) {
                    sb.append("  ");
                }
            }
            sb.append("\n");
            if (i == 0) {
                int total = 0;
                for (int ancho : anchos) {
                    total += ancho;
                }
                sb.append("-".repeat(total + 2 * (columnas - 1))).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String renderPie(String texto) {
        return "-- " + texto + " --\n";
    }

    @Override
    public String saltoDePagina(int numero) {
        return "[ pagina " + numero + " ]\n";
    }

    @Override
    public String finalizar() {
        return "%%EOF\n";
    }
}
