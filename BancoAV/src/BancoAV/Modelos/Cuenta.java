package BancoAV.Modelos;

public class Cuenta extends Producto{

    private double cupoSobreGiro;
    private double saldo;

    public Cuenta(String productoId, String clienteId, String producto, double CupoSobreGiro, double saldo) {
        super(productoId, clienteId, producto);
        this.cupoSobreGiro = cupoSobreGiro;
        this.saldo = saldo;
    }

    public double getCupoSobreGiro() {
        return cupoSobreGiro;
    }

    public void setCupoSobreGiro(double cupoSobreGiro) {
        this.cupoSobreGiro = cupoSobreGiro;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {return  super.toString() + ',' + saldo + ',' + cupoSobreGiro + '\n';}
}
