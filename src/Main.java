import interpreter.Context;
import interpreter.Expression;
import interpreter.ExpressionParser;
import mediator.BarraDeHerramientasBuilder;
import mediator.BotonExportar;
import mediator.PanelControlMediator;
import mediator.SelectorDeFormato;
import mediator.VistaPrevia;

public class Main {

    public static void main(String[] args) {
        SelectorDeFormato selector = new SelectorDeFormato();
        BarraDeHerramientasBuilder barra = new BarraDeHerramientasBuilder();
        VistaPrevia vista = new VistaPrevia();
        BotonExportar boton = new BotonExportar();
        PanelControlMediator panel = new PanelControlMediator(selector, barra, vista, boton);

        paso("PASO 1 Configuracion via Mediator: reporte ejecutivo en formato PDF");
        barra.seleccionarTipo(BarraDeHerramientasBuilder.REPORTE_EJECUTIVO);
        selector.seleccionarFormato(SelectorDeFormato.PDF);

        paso("PASO 2 Exportar: el Mediator usa el Builder con Flyweights, la Chain of Responsibility, el Interpreter y el Bridge");
        boton.presionar();

        paso("PASO 3 Segundo formato: el Mediator cambia el motor de render a HTML");
        selector.seleccionarFormato(SelectorDeFormato.HTML);
        boton.presionar();

        paso("PASO 4 Tercer formato y otro tipo de documento: factura simple en Markdown");
        barra.seleccionarTipo(BarraDeHerramientasBuilder.FACTURA_SIMPLE);
        selector.seleccionarFormato(SelectorDeFormato.MARKDOWN);
        boton.presionar();

        paso("PASO 5 Evaluacion de formulas con el Interpreter");
        Context contexto = new Context()
                .define("PRECIO_BASE", 1000)
                .define("DESCUENTO", 50)
                .define("CANTIDAD", 3);
        evaluar("PRECIO_BASE * 1.19 - DESCUENTO", contexto);
        evaluar("DESCUENTO + CANTIDAD * 10", contexto);
        evaluar("(DESCUENTO + CANTIDAD) * 10", contexto);
        evaluar("FECHA_ACTUAL", contexto);
        evaluar("PRECIO_BASE * VARIABLE_INEXISTENTE", contexto);

        paso("PASO 6 La Chain of Responsibility se interrumpe ante un error critico");
        panel.setContexto(new Context());
        boton.presionar();

        paso("RESUMEN FINAL");
        System.out.println("Tipo de documento: " + panel.getTipoActual());
        System.out.println("Motor de render: " + panel.getMotorActual().getNombre());
        System.out.println("Objetos Flyweight en el pool: " + panel.getFabrica().getPoolSize());
        System.out.println("Solicitudes al pool: " + panel.getFabrica().getTotalRequests());
    }

    private static void evaluar(String formula, Context contexto) {
        System.out.println("Formula: " + formula);
        try {
            Expression expresion = ExpressionParser.parse(formula);
            System.out.println("Resultado: " + expresion.interpret(contexto));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void paso(String texto) {
        System.out.println();
        System.out.println(texto);
    }
}