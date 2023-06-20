package Modelos;
public class CuentaDestino {
    //atributos cuenta destino
    private String numeroCuentaDestino;
    private String cedula;

    //controlador
    public CuentaDestino(String numeroCuentaDestino, String cedula) {
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.cedula = cedula;
    }

    //getters y setters
    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }
    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    // to String
    @Override
    public String toString() {
        return "CUENTA DESTINO: " + numeroCuentaDestino + " CEDULA: " + cedula;
    }
}
