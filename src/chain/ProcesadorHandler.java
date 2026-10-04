package chain;


public abstract class ProcesadorHandler {

    private ProcesadorHandler siguiente;


    public ProcesadorHandler setSiguiente(ProcesadorHandler siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public ResultadoProceso procesar(DocumentoEnProceso doc) {
        ResultadoProceso resultado = hacerProceso(doc);
        System.out.println("[" + getClass().getSimpleName() + "] " + resultado.getMensaje());


        if (resultado.isErrorCritico() || siguiente == null) {
            return resultado;
        }
        return siguiente.procesar(doc);
    }


    protected abstract ResultadoProceso hacerProceso(DocumentoEnProceso doc);
}
