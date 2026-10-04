package mediator;
import mediator.SelectorDeFormato;

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
        selector.seleccionar(SelectorDeFormato.PDF);

        System.out.println();
        System.out.println("--- 4. Exportar ---");
        boton.presionar();

        System.out.println();
        System.out.println("--- 5. Cambiar formato a HTML y exportar ---");
        selector.seleccionar(SelectorDeFormato.HTML);
        boton.presionar();

        System.out.println();
        System.out.println("Builder final: " + panel.getBuilderActual());
        System.out.println("Motor final: " + panel.getMotorActual());
    }
}