package BancoAV.Modelos;

import java.util.ArrayList;

public class Cliente {
    private String numeroId;
    private String tipoId;
    private String nombre;
    private String apellido;
    private String direccion;
    private String celular;
    private String correo;
    private String tipoEmpleo;
    private double ingresosMensuales;
    private ArrayList<String> productos;

    public Cliente(String numeroId, String tipoId, String nombre, String apellido, String direccion, String celular, String correo, String tipoEmpleo, double ingresosMensuales, ArrayList<String> productos) {
        this.numeroId = numeroId;
        this.tipoId = tipoId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.celular = celular;
        this.correo = correo;
        this.tipoEmpleo = tipoEmpleo;
        this.ingresosMensuales = ingresosMensuales;
        this.productos = productos;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoEmpleo() {
        return tipoEmpleo;
    }

    public void setTipoEmpleo(String tipoEmpleo) {
        this.tipoEmpleo = tipoEmpleo;
    }

    public double getIngresosMensuales() {
        return ingresosMensuales;
    }

    public void setIngresosMensuales(double ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }

    public ArrayList getProductos() {
        return productos;
    }

    public void setProductos(ArrayList productos) {
        this.productos = productos;
    }

    /*@Override
    public String toString() {
        return "Cliente-" + numeroId + " {" +'\n' +
                "numeroId='" + numeroId + ";" + '\n' +
                "tipoId='" + tipoId + ";" + '\n' +
                "nombre='" + nombre + ";" + '\n' +
                "apellido='" + apellido + ";" + '\n' +
                "direccion='" + direccion + ";" + '\n' +
                "celular='" + celular + ";" + '\n' +
                "correo='" + correo + ";" + '\n' +
                "tipoEmpleo='" + tipoEmpleo + ";" + '\n' +
                "ingresosMensuales=" + ingresosMensuales + ";" + '\n' +
                "productos=" + productos + ";" + '\n' +
        '}';
    }*/
    @Override
    public String toString() {
        return numeroId + ',' + tipoId + ',' + nombre + ',' + apellido + ',' + direccion + ','+ celular + ',' + correo + ',' + tipoEmpleo + ',' + ingresosMensuales + ',' + productos + '\n';
    }
}
