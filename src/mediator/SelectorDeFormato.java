package mediator;

public class SelectorDeFormato extends ComponenteUI{
    private static final String PDF = "PDF";
    private static final String HTML = "HTML";
    private static final String MARKDOWN = "MARKDOWN";

    private String formato;

    public SelectorDeFormato() {
        super("SelectorDeFormato");
        this.formato = null;
    }

    public void seleccionarFormato(String nuevoFormato) {
        if (!PDF.equals(nuevoFormato) && !HTML.equals(nuevoFormato) && !MARKDOWN.equals(nuevoFormato)){
            throw new IllegalArgumentException("Formato no soportado: " + nuevoFormato + ". Por favor ingrese un formato valido (PDF, HTML, MARKDOWN).");
        }
        this.formato = nuevoFormato;
        System.out.println("[" + getNombre() + "] El usuario eligio el formato " + nuevoFormato);
        notificarCambio(DocumentEditorMediator.FORMATO_CAMBIADO);
    }

    public String getFormato() {
        return formato;
    }
}