package mediator;

public interface DocumentEditorMediator {
    String FORMATO_CAMBIADO = "FORMATO_CAMBIADO";
    String TIPO_DOCUMENTO_CAMBIADO = "TIPO_DOCUMENTO_CAMBIADO";
    String EXPORTAR_SOLICITADO = "EXPORTAR_SOLICITADO";

    void notificar(ComponenteUI emisor, String evento);
}