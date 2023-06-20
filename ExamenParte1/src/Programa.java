import Modelos.CuentaOrigen;
import Modelos.Empresa;
import Modelos.Funcionario;

import Controladores.ControladorFuncionario;
import Controladores.ControladorEmpresa;
import Controladores.ControladorCuentaOrigen;
import Controladores.ControladorCuentaDestino;
import Controladores.ControladorTransaccion;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Programa {
    static Funcionario conectado;
    static Scanner sc = new Scanner(System.in);

    public static void Inicio() throws ParseException, IOException {
        // login
        System.out.print("Usuario: ");
        String usuario = sc.next();
        System.out.print("Contraseña: ");
        String contraseña = sc.next();

        // inicio de sesion
        conectado = ControladorFuncionario.iniciarSesion(usuario, contraseña);
        if(conectado.isEstado()) {
            System.out.println("INICIO DE SESIÓN EXITOSO!\n");
            Empresa empresa = ControladorEmpresa.registrar();// registro Nit empresa
            ControladorCuentaOrigen.registrar(empresa.getNit());// registro cuenta Origen
            mostrarmenu();// menu
        }
    }
    // Menu con las 2 funcionalidades requeridas y la salida del programa
    public static void mostrarmenu() throws IOException {
        int opcion;

        do{
            System.out.println("\n1. Asociar Cuenta nomina a cuenta origen");
            System.out.println("2. Realizar transferencia por pago de nómina");
            System.out.println("3. Cerrar Sesión");
            System.out.print("\nINGRESE UNA OPCIÓN:");

            opcion=sc.nextInt();

            switch (opcion) {
                case 1 -> ControladorCuentaDestino.asociarCuenta();
                case 2 -> ControladorTransaccion.revisarAsociacion();
                case 3 -> System.out.println("EL PROGRAMA HA TERMINADO");
                default -> System.out.println("LA OPCIÓN " + opcion + " NO ES VALIDA");
            }

        }while(opcion!=3);
    }
}
