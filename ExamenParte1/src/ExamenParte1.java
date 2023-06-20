import java.io.IOException;
import java.text.ParseException;
/*
autor: Diego Sandoval
Fecha: 17-06-2023
Descripción: Programa para la asociación de cuentas de nomina AV Villas a una cuenta origen de cualquier empresa
             con convenio con el banco. Permite el registro de las asociaciones de cuentas de nomina a la cuenta
             origen de la empresa y el registro de los valores de transferencia por pago de nómina realizados
             diariamente. Solo se puede acceder mediante un logeo de usuario y contraseña registrados en un
             archivo de configuración.
*/
public class ExamenParte1 {
    public static void main(String[] args) throws ParseException, IOException {
        try{
            System.out.println("BIENVENIDO AL SISTEMA DE GESTIÓN DE CONVENIO DE NOMINA CON AV VILLAS");
            Programa.Inicio();
        }
        catch (IOException e){
            System.out.println("Error "+e);
        }
    }
}
