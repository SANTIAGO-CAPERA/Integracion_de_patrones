package mediator;

public class ComponenteUI {
    
    protected DocumentEditorMediator mediator;
    private final String nombre;

    public ComponenteUI(String nombre) {
        this.nombre = nombre;
    }

    public void setMediator(DocumentEditorMediator mediator) {
        this.mediator = mediator;
    }

    public void notificarCambio(String evento) {
        if (mediator != null) {
            mediator.notificar(this, evento);
        }
    }

    public String getNombre() {
        return nombre;
    }
}