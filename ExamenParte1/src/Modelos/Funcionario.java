package Modelos;
public class Funcionario {
    //Atributos Funcionario
    private String usuario;
    private String contraseña;
    private boolean estado;

    //Controlador
    public Funcionario(String usuario, String contraseña, boolean estado) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.estado = estado;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

