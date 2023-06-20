package Controladores;
import Excepciones.ExcepcionContraseñaIncorrecta;
import Excepciones.ExcepcionUsuarioIncorrecto;
import Excepciones.ExcepcionArchivoInvalido;

import Modelos.Funcionario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControladorFuncionario {
    private static Funcionario cargarDatos() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("CONFIGURACION.txt"));
            String linea;
            String usuario = null;
            String contraseña = null;
            // leer el archivo linea a linea y extrae el usuario y contraseña con los valores usando clave/valor
            while ((linea = reader.readLine()) != null) {
                // evitar crear un objeto con usuario = " admin" y contraseña = " 123456" (espacios en blanco)
                if (linea.contains(": ")) throw new ExcepcionArchivoInvalido("FORMATO DE ARCHIVO INCORRECTO. VERIFIQUE EL ARCHIVO CONFIGURACION.TXT");
                else {
                    String[] partes = linea.split(":");
                    String clave = partes[0].trim();
                    String valor = partes[1].trim();
                    if (clave.equals("usuario")) usuario = valor;
                    else if (clave.equals("contraseña")) contraseña = valor;
                }
            }
            // si el archivo es correcto crea y retorna un objeto Funcionario en estado False (aun no conectado)
            if (usuario != null && contraseña != null) return new Funcionario(usuario, contraseña, false);
            else throw new ExcepcionArchivoInvalido("EL ARCHIVO NO CONTIENE LOS DATOS NECESARIOS. VERIFIQUE EL ARCHIVO CONFIGURACION.TXT");
        } catch (IOException e) {
            throw new ExcepcionArchivoInvalido("ERROR AL LEER EL ARCHIVO");
        }
    }
    // Se crea nuevo objeto Funcionario llamado conectado llamando a la función cargar datos
    private static Funcionario conectado = cargarDatos();

    // Funcion inicio de sesion
    public static Funcionario iniciarSesion(String usuario, String contraseña) {
        if (!usuario.equals(conectado.getUsuario())) throw new ExcepcionUsuarioIncorrecto("USUARIO INCORRECTO");
        else if (!contraseña.equals(conectado.getContraseña())) throw new ExcepcionContraseñaIncorrecta("CONTRASEÑA INCORRECTA");
        else {
            conectado.setEstado(true);
            return conectado;
        }
    }
}
