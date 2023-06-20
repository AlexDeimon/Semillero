package Modelos;
public class Transaccion {
    //atributos transaccion
    private String numeroCuentaDestino;
    private int valor;

    //controlador
    public Transaccion(String numeroCuentaDestino, int valor) {
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.valor = valor;
    }

    //getters y setters
    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }
    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }

    // to String
    @Override
    public String toString() {
        return "CUENTA DESTINO: " + numeroCuentaDestino + " VALOR: " + valor;
    }
}
