package chain;


public class ResultadoProceso {

    private boolean exito;
    private boolean errorCritico;
    private String mensaje;

    public ResultadoProceso(boolean exito, boolean errorCritico, String mensaje) {
        this.exito = exito;
        this.errorCritico = errorCritico;
        this.mensaje = mensaje;
    }

    public static ResultadoProceso ok(String mensaje) {
        return new ResultadoProceso(true, false, mensaje);
    }

    public static ResultadoProceso critico(String mensaje) {
        return new ResultadoProceso(false, true, mensaje);
    }

    public boolean isExito() { return exito; }
    public boolean isErrorCritico() { return errorCritico; }
    public String getMensaje() { return mensaje; }
}

