package BancoAV.Controladores;
import BancoAV.Modelos.Funcionario;
import BancoAV.Excepciones.ExcepcionUsuarioIncorrecto;
import BancoAV.Excepciones.ExcepcionContraseñaIncorrecta;

public class ControladorFuncionario {

    private static Funcionario conectado = new Funcionario("admin", "123456", false);

    public static Funcionario iniciarSesion(String usuario, String contraseña){
        if(!usuario.equals(conectado.getUsuario())){
            throw new ExcepcionUsuarioIncorrecto("El usuario es incorrecto");
        } else if(!contraseña.equals(conectado.getContraseña())){
            throw new ExcepcionContraseñaIncorrecta("La contraseña es incorrecta");
        } else {
            conectado.setEstado(true);
            return conectado;
        }
    }
}
