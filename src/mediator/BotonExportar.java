package mediator;

public class BotonExportar extends ComponenteUI{
    private boolean habilitado;

    public BotonExportar() {
        super("BotonExportar");
        this.habilitado = false;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
        System.out.println("[" + getNombre() + "] " + (habilitado ? "Habilitado" : "Deshabilitado"));
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void presionar() {
        if (!habilitado) {
            System.out.println("[" + getNombre() + "] Click ignorado: falta elegir tipo y formato");
            return;
        }
        System.out.println("[" + getNombre() + "] El usuario presiono Exportar");
        notificarCambio(DocumentEditorMediator.EXPORTAR_SOLICITADO);
    }
}
