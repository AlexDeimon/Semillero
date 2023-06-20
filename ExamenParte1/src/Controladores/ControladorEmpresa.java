package Controladores;
import Modelos.Empresa;

import Excepciones.ExcepcionNitEmpresaInvalido;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ControladorEmpresa {
    static Scanner sc = new Scanner(System.in);
    static String patron = "\\d{10}"; // Expresión regular para cadena de 10 numeros
    public static Empresa registrar(){
        System.out.print("Digite el nit de la empresa: ");
        String nitEmpresa = sc.next();
        if(Pattern.matches(patron, nitEmpresa)) return new Empresa(nitEmpresa);
        else throw new ExcepcionNitEmpresaInvalido("El NIT DEBE TENER EXACTAMENTE 10 CARACTERES NUMERICOS");
    }
}
