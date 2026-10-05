package mediator;

import interpreter.Context;

public class MediatorDemo {

    public static void main(String[] args) {
        SelectorDeFormato selector = new SelectorDeFormato();
        BarraDeHerramientasBuilder barra = new BarraDeHerramientasBuilder();
        VistaPrevia vista = new VistaPrevia();
        BotonExportar boton = new BotonExportar();

        PanelControlMediator panel = new PanelControlMediator(selector, barra, vista, boton);

        System.out.println("--- 1. Exportar sin configurar ---");
        boton.presionar();

        System.out.println();
        System.out.println("--- 2. Elegir tipo de documento ---");
        barra.seleccionarTipo(BarraDeHerramientasBuilder.FACTURA_SIMPLE);

        System.out.println();
        System.out.println("--- 3. Elegir formato PDF ---");
        selector.seleccionarFormato(SelectorDeFormato.PDF);

        System.out.println();
        System.out.println("--- 4. Exportar ---");
        boton.presionar();

        System.out.println();
        System.out.println("--- 5. Cambiar formato a HTML y exportar ---");
        selector.seleccionarFormato(SelectorDeFormato.HTML);
        boton.presionar();

        System.out.println();
        System.out.println("--- 6. Cambiar a reporte ejecutivo en Markdown y exportar ---");
        barra.seleccionarTipo(BarraDeHerramientasBuilder.REPORTE_EJECUTIVO);
        selector.seleccionarFormato(SelectorDeFormato.MARKDOWN);
        boton.presionar();

        System.out.println();
        System.out.println("--- 7. Exportar con un contexto sin variables (la cadena se interrumpe) ---");
        panel.setContexto(new Context());
        boton.presionar();

        System.out.println();
        System.out.println("Tipo de documento final: " + panel.getTipoActual());
        System.out.println("Motor final: " + panel.getMotorActual().getNombre());
    }
}
