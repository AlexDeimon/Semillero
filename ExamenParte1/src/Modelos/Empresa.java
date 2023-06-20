package Modelos;
public class Empresa {
    //atributo empresa
    private String nit;

    //controlador
    public Empresa(String nit) {
        this.nit = nit;
    }

    //getter y setter
    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }

    // to String
    @Override
    public String toString() {
        return "NIT DE LA EMPRESA: " + nit;
    }
}
