package BancoAV.Controladores;
import BancoAV.Excepciones.ExcepcionClienteInexistente;
import BancoAV.Modelos.Cliente;
import BancoAV.Excepciones.ExcepcionDatoInvalido;
import BancoAV.Excepciones.ExcepcionClienteExistente;
import java.io.*;
import java.util.*;

public class ControladorCliente {
    static Scanner sc = new Scanner(System.in);

    public static Cliente crearCliente(){
        System.out.print("CREAR CLIENTE\n");

        System.out.print("Ingrese el número de identificación del cliente: ");
        String numeroId = sc.nextLine();

        System.out.print("Ingrese el tipo de identificación del cliente: ");
        String tipoId = sc.nextLine();

        System.out.print("Ingrese los nombres del cliente: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese los apellidos del cliente: ");
        String apellido = sc.nextLine();

        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = sc.nextLine();

        System.out.print("Ingrese el número de celular del cliente: ");
        String celular = sc.nextLine();

        System.out.print("Ingrese el correo electrónico del cliente: ");
        String correo = sc.nextLine();

        System.out.print("Ingrese el tipo de empleo del cliente: ");
        String tipoEmpleo = sc.nextLine();

        System.out.print("Ingrese los ingresos mensuales del cliente: ");
        double ingresosMensuales = Double.parseDouble(sc.nextLine());

        ArrayList producto = new ArrayList();

        if(numeroId.isEmpty() || tipoId.isEmpty()  || nombre.isEmpty()  || apellido.isEmpty()  || direccion.isEmpty()  || celular.isEmpty()  || correo.isEmpty()  || tipoEmpleo.isEmpty()){
            throw new ExcepcionDatoInvalido("Ningun dato debe ser nulo");
        }
        try {
            Integer.parseInt(numeroId);
        } catch (NumberFormatException e){
            throw new ExcepcionDatoInvalido("Número de identificación del cliente inválido");
        }

        try{
            BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[0].equals(numeroId) || (atributos[2].equals(nombre) && atributos[3].equals(apellido))) {
                    throw new ExcepcionClienteExistente("El cliente ya existe");
                }
            }
        }catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }

        Cliente cliente = new Cliente(numeroId, tipoId, nombre, apellido, direccion, celular, correo, tipoEmpleo, ingresosMensuales, producto);

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("clientes.txt", true));
            pw.print(cliente.toString());
            pw.close();
            System.out.println("\nCLIENTE "+ nombre +" "+ apellido +" CREADO EXITOSAMENTE");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cliente;
    }
    public static void listarClientes(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"));
            String linea;
            System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s %-25s %-15s %-20s %s%n", "ID", "TIPO ID", "NOMBRES", "APELLIDOS", "DIRECCIÓN", "CELULAR", "CORREO", "TIPO DE EMPLEO", "INGRESOS MENSUALES", "PRODUCTOS");
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s %-25s %-15s %-20s %s%n", atributos[0], atributos[1], atributos[2], atributos[3], atributos[4], atributos[5], atributos[6], atributos[7], atributos[8], atributos[9]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }
    }

    public static void buscarCliente(){
        System.out.print("Ingrese el número de identificación del cliente: ");
        String clienteId = sc.nextLine();
        if(clienteId.isEmpty()) {
            throw new ExcepcionDatoInvalido("Debes ingresar un número de identificación del cliente");
        }

        try {
            Integer.parseInt(clienteId);
        } catch (NumberFormatException e){
            throw new ExcepcionDatoInvalido("Número de identificación del cliente inválido");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"));
            String linea;
            boolean existeCliente = false;
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[0].equals(clienteId)) {
                    existeCliente = true;
                    break;
                }
            }
            if (!existeCliente) {
                throw new ExcepcionClienteInexistente("El cliente no existe");
            }
        }catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"));
            String linea;
            System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s %-25s %-15s %-20s %s%n", "ID", "TIPO ID", "NOMBRES", "APELLIDOS", "DIRECCIÓN", "CELULAR", "CORREO", "TIPO DE EMPLEO", "INGRESOS MENSUALES", "PRODUCTOS");
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[0].equals(clienteId)) {
                    System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s %-25s %-15s %-20s %s%n", atributos[0], atributos[1], atributos[2], atributos[3], atributos[4], atributos[5], atributos[6], atributos[7], atributos[8], atributos[9]);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }
    }

}
