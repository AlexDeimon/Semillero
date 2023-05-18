package BancoAV.Modelos;

import java.util.Date;

public class Transaccion {
    private String transaccionId;
    private String tipoTransaccion;
    private String productoId;
    private double monto;
    private String canal;
    private Date fecha;

    public Transaccion(String transaccionId, String tipoTransaccion, String productoId, double monto, String canal, Date fecha) {
        this.transaccionId = transaccionId;
        this.tipoTransaccion = tipoTransaccion;
        this.productoId = productoId;
        this.monto = monto;
        this.canal = canal;
        this.fecha = fecha;
    }

    public String getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return transaccionId + "," + tipoTransaccion + "," + productoId + "," + monto + "," + canal + "," + fecha + '\n';}
}
