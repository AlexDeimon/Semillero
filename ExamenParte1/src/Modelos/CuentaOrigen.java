package Modelos;
public class CuentaOrigen {
    //atributo cuenta origen
    private String numeroCuentaOrigen;

    //controlador
    public CuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    //getter y setter
    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }
    public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    // to String
    @Override
    public String toString() {
        return "CUENTA ORIGEN: " + numeroCuentaOrigen;
    }
}
