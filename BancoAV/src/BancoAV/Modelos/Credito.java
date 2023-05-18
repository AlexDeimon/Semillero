package BancoAV.Modelos;

public class Credito extends Producto{
    private double valorSolicitado;
    private double valorCuota;
    private double tasaInteres;
    private double tiempo;
    private boolean aprobado;

    public Credito(String productoId, String clienteId, String producto, double valorSolicitado, double valorCuota, double tasaInteres, double tiempo, boolean aprobado) {
        super(productoId, clienteId, producto);
        this.valorSolicitado = valorSolicitado;
        this.valorCuota = valorCuota;
        this.tasaInteres = tasaInteres;
        this.tiempo = tiempo;
        this.aprobado = aprobado;
    }

    public double getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(double valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    @Override
    public String toString() {return  super.toString() + ',' + valorSolicitado + ',' + valorCuota + ',' + tasaInteres + ',' + tiempo + ',' + aprobado + '\n';}
}
