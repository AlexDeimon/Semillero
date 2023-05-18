package BancoAV.Controladores;
import BancoAV.Excepciones.ExcepcionClienteInexistente;
import BancoAV.Excepciones.ExcepcionDatoInvalido;
import BancoAV.Excepciones.ExcepcionProductoInexistente;
import BancoAV.Modelos.Cuenta;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ControladorCuenta {
    static Scanner sc = new Scanner(System.in);

    public static void crearCuenta() throws IOException {
        System.out.print("CREAR CUENTA\n");
        Random random = new Random();
        int numAleatorio = random.nextInt(900000000) + 100000000;

        System.out.print("Ingrese la identificación del cliente: ");
        String clienteId = sc.next();

        if(clienteId.isEmpty()) {
            throw new ExcepcionDatoInvalido("Debes ingresar un número de identificación del cliente");
        }

        try {
            Integer.parseInt(clienteId);
        } catch (NumberFormatException e){
            throw new ExcepcionDatoInvalido("Numero de identificación del cliente inválido");
        }

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

        System.out.print("Ingrese el numero del tipo de cuenta (1. rentavillas, 2. tu giro): ");
        int numero = Integer.parseInt(sc.next());
        String productoId;
        String producto;
        switch (numero){
            case 1:{
                productoId = "AHO-" + Integer.toString(numAleatorio);
                producto = "rentavillas";
            }break;
            case 2: {
                productoId = "CTE-" + Integer.toString(numAleatorio);
                producto = "tu giro";
            }break;
            default: throw new ExcepcionDatoInvalido("Debes ingresar una opción correcta");
        }

        Path archivoClientes = Paths.get("clientes.txt");
        List<String> lineas = Files.readAllLines(archivoClientes);
        String listaProductos;
        double cupoSobregiro = 0.0;
        Cuenta nuevoProducto = new Cuenta(productoId, clienteId, producto, 0.0, 0.0);
        for (int i = 0; i < lineas.size(); i++) {
            String[] atributos = lineas.get(i).split(",");
            if (atributos[0].equals(clienteId)) {
                ArrayList<String> productos = new ArrayList<>();
                if (!atributos[9].equals("[]")) {
                    String[] atributoProductos = atributos[9].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
                    for (String atributoProducto : atributoProductos) {
                        productos.add(atributoProducto.trim());
                    }
                }
                if(producto.equals("tu giro")){
                    cupoSobregiro = Double.parseDouble(atributos[8]) * 0.3;
                    nuevoProducto.setCupoSobreGiro(cupoSobregiro);
                }
                productos.add(nuevoProducto.getProductoId());
                listaProductos = "["+String.join(";", productos)+"]";
                atributos[9] = listaProductos;
                lineas.set(i, String.join(",", atributos));
                PrintWriter pw = new PrintWriter(new FileWriter("productos.txt", true));
                String clienteProducto = nuevoProducto.toString();
                pw.print(clienteProducto);
                pw.close();
                break;
            }
        }
        Files.write(archivoClientes, lineas);
        System.out.println("\nPRODUCTO "+ productoId +" CREADO EXITOSAMENTE");
    }

    public static String buscarSaldo(){
        System.out.print("Ingrese el id del producto: ");
        String producto = sc.next();
        String saldo = "";
        if(producto.isEmpty()) {
            throw new ExcepcionDatoInvalido("Debes ingresar un id de producto");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("productos.txt"));
            String linea;
            boolean existeProducto = false;
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[0].equals(producto)) {
                    existeProducto = true;
                    break;
                }
            }
            if (!existeProducto) {
                throw new ExcepcionProductoInexistente("El producto no existe");
            }
        }catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("productos.txt"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[0].equals(producto)) {
                    saldo = "EL SALDO DEL PRODUCTO " + producto + " ES $" + atributos[3];
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }
        return saldo;
    }

    public static void listarCuentas(){
        System.out.print("Ingrese el número de identificación del cliente: ");
        String clienteId = sc.next();

        if(clienteId.isEmpty()) {
            throw new ExcepcionDatoInvalido("Debes ingresar un número de identificación del cliente");
        }

        try {
            Integer.parseInt(clienteId);
        } catch (NumberFormatException e){
            throw new ExcepcionDatoInvalido("Número de identificación del cliente inválido");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("productos.txt"));
            String linea;
            boolean existeCliente = false;
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[1].equals(clienteId)) {
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
            BufferedReader reader = new BufferedReader(new FileReader("productos.txt"));
            String linea;
            System.out.println("CUENTAS AHORRO / CORRIENTE");
            System.out.printf("%-15s %-25s %-25s %-25s %s%n", "ID PRODUCTO", "ID CLIENTE", "PRODUCTO", "SALDO", "CUPO SOBREGIRO");
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[1].equals(clienteId) && (atributos[2].equals("rentavillas") || atributos[2].equals("tu giro"))) {
                    System.out.printf("%-15s %-25s %-25s %-25s %s%n", atributos[0], atributos[1], atributos[2], "$ "+atributos[3], "$ "+atributos[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }
    }

    public static void depositar() throws IOException {
        System.out.print("Ingrese el id de la cuenta: ");
        String producto = sc.next();

        if(producto.isEmpty()) {
            throw new ExcepcionDatoInvalido("Debes ingresar un id de producto");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("productos.txt"));
            String linea;
            boolean existeProducto = false;
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[0].equals(producto)) {
                    existeProducto = true;
                    break;
                }
            }
            if (!existeProducto) {
                throw new ExcepcionProductoInexistente("El producto no existe");
            }
        }catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }

        System.out.print("Ingrese el monto a depositar: ");
        double monto = Double.parseDouble(sc.next());

        Path archivoProductos = Paths.get("productos.txt");
        List<String> lineas = Files.readAllLines(archivoProductos);
        for(int i = 0; i < lineas.size(); i++){
            String[] atributos = lineas.get(i).split(",");
            if(atributos[0].equals(producto)){
                System.out.println("CUENTA "+ atributos[0]);
                System.out.println("VALOR ANTES DEL DEPOSITO: "+ atributos[3]);
                System.out.println("MONTO:"+ monto);
                atributos[3] = Double.toString(Double.parseDouble(atributos[3]) + monto);
                System.out.println("VALOR DESPUES DEL DEPOSITO: "+ atributos[3]);
                lineas.set(i, String.join(",", atributos));
                break;
            }
        }
        Files.write(archivoProductos, lineas);
    }

    public static void retirar() throws IOException {
        System.out.print("Ingrese el id de la cuenta: ");
        String producto = sc.next();

        if(producto.isEmpty()) {
            throw new ExcepcionDatoInvalido("Debes ingresar un id de producto");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("productos.txt"));
            String linea;
            boolean existeProducto = false;
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[0].equals(producto)) {
                    existeProducto = true;
                    break;
                }
            }
            if (!existeProducto) {
                throw new ExcepcionProductoInexistente("El producto no existe");
            }
        }catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }

        System.out.print("Ingrese el monto a retirar: ");
        double monto = Double.parseDouble(sc.next());

        Path archivoProductos = Paths.get("productos.txt");
        List<String> lineas = Files.readAllLines(archivoProductos);
        for(int i = 0; i < lineas.size(); i++){
            String[] atributos = lineas.get(i).split(",");
            if(atributos[0].equals(producto)){
                System.out.println("CUENTA "+ atributos[0]);
                System.out.println("MONTO:"+ monto);
                if((atributos[2].equals("rentavillas") || atributos[2].equals("tu giro")) && Double.parseDouble(atributos[3])>=monto){
                    System.out.println("VALOR ANTES DEL RETIRO: "+ atributos[3]);
                    atributos[3] = Double.toString(Double.parseDouble(atributos[3]) - monto);
                    System.out.println("VALOR DESPUES DEL RETIRO: "+ atributos[3]);
                    lineas.set(i, String.join(",", atributos));
                    break;
                } else if (atributos[2].equals("rentavillas") && Double.parseDouble(atributos[3])<monto) {
                    System.out.println("EL VALOR DEL MONTO A RETIRAR ES MAYOR QUE EL SALDO");break;
                } else if (atributos[2].equals("tu giro") && Double.parseDouble(atributos[3])<monto && Double.parseDouble(atributos[4])>=monto){
                    System.out.println("TU SALDO ESTÁ EN 0 PERO TIENES DISPONIBLE EN TU CUPO DE SOBREGIRO: "+ atributos[4]);
                    System.out.println("¿DESEA RETIRAR DE SU CUPO DE SOBREGIRO? (Y/N)");
                    String opcion = sc.next();
                    if(opcion.equals("Y")){
                        System.out.println("VALOR CUPO ANTES DEL RETIRO: "+ atributos[4]);
                        atributos[4] = Double.toString(Double.parseDouble(atributos[4]) - monto);
                        System.out.println("VALOR CUPO DESPUES DEL RETIRO: "+ atributos[4]);
                        lineas.set(i, String.join(",", atributos));
                    } else System.out.println("NO SE REALIZÓ RETIRO");
                } else if (atributos[2].equals("tu giro") && Double.parseDouble(atributos[3])<monto && Double.parseDouble(atributos[4])<monto){
                    System.out.println("EL VALOR DEL MONTO A RETIRAR ES MAYOR QUE EL SALDO Y NO TIENES CUPO SOBREGIRO DISPONIBLE PARA ESTE RETIRO");break;
                }
            }

        }
        Files.write(archivoProductos, lineas);
    }
}
