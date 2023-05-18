package BancoAV;
import BancoAV.Modelos.Funcionario;
import BancoAV.Controladores.ControladorCredito;
import BancoAV.Controladores.ControladorCliente;
import BancoAV.Controladores.ControladorFuncionario;
import BancoAV.Controladores.ControladorCuenta;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class BancoAV {
    static Funcionario conectado;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ParseException, IOException {
        System.out.print("Usuario: ");
        String usuario = sc.next();
        System.out.print("Contraseña: ");
        String contraseña = sc.next();
        conectado = ControladorFuncionario.iniciarSesion(usuario, contraseña);
        if(conectado.isEstado()) {
            mostrarMenu();
        }
    }

    private static void mostrarMenu() throws IOException {
        int opcion;
        do{
            System.out.println("\nBIENVENIDO AL SISTEMA DE BANCO AV \n");
            System.out.println("1.  Crear nuevo Cliente");
            System.out.println("2.  Consultar Clientes");
            System.out.println("3.  Buscar cliente especifico");
            System.out.println("4.  Crear nueva Cuenta");
            System.out.println("5.  Consultar Cuentas");
            System.out.println("6   Consultar Saldo");
            System.out.println("7.  Solicitar Credito");
            System.out.println("8.  Consultar Datos Creditos");
            System.out.println("9.  Realizar Deposito");
            System.out.println("10. Realizar Retiro");
            System.out.println(".   Consultar movimientos");
            System.out.println(".   Consultar movimientos de cuenta especifica");
            System.out.println("13. Cerrar Sesión");
            System.out.print("\nINGRESE UNA OPCIÓN:");
            opcion=sc.nextInt();
            if(opcion>=1 && opcion<=13){
                switch (opcion) {
                    case 1: ControladorCliente.crearCliente();break;
                    case 2: ControladorCliente.listarClientes();break;
                    case 3: ControladorCliente.buscarCliente();break;
                    case 4: ControladorCuenta.crearCuenta();break;
                    case 5: ControladorCuenta.listarCuentas();break;
                    case 6: System.out.println(ControladorCuenta.buscarSaldo());break;
                    case 7: ControladorCredito.solicitarCredito();break;
                    case 8: ControladorCredito.listarCreditos();break;
                    case 9: ControladorCuenta.depositar();break;
                    case 10: ControladorCuenta.retirar();break;
                    case 13: System.out.println("\nEL PROGRAMA HA TERMINADO");break;
                    default: System.out.println("\nLA OPCIÓN " + opcion + " LLEGARA PRONTO");
                }
            } else {
                System.out.println("\nLA OPCIÓN ES INVÁLIDA");
            }
        }while(opcion!=13);
    }
}