package mediator;

public class BarraDeHerramientasBuilder extends ComponenteUI{
    public static final String REPORTE_EJECUTIVO = "REPORTE_EJECUTIVO";
    public static final String FACTURA_SIMPLE = "FACTURA_SIMPLE";

    private String tipoDocumento;

    public BarraDeHerramientasBuilder() {
        super("BarraDeHerramientasBuilder");
        this.tipoDocumento = null;
    }

    public void seleccionarTipo(String nuevoTipo) {
        if (!REPORTE_EJECUTIVO.equals(nuevoTipo) && !FACTURA_SIMPLE.equals(nuevoTipo)) {
            throw new IllegalArgumentException("Tipo de documento no soportado: " + nuevoTipo + ". Por favor ingrese un tipo valido (REPORTE_EJECUTIVO, FACTURA_SIMPLE).");
        }
        this.tipoDocumento = nuevoTipo;
        System.out.println("[" + getNombre() + "] El usuario eligio el tipo " + nuevoTipo);
        notificarCambio(DocumentEditorMediator.TIPO_DOCUMENTO_CAMBIADO);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }
}