package BancoAV.Controladores;
import BancoAV.Excepciones.ExcepcionClienteInexistente;
import BancoAV.Excepciones.ExcepcionDatoInvalido;
import BancoAV.Modelos.Credito;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ControladorCredito {
    static Scanner sc = new Scanner(System.in);

    public static void solicitarCredito() throws IOException{
        Random random = new Random();
        int numAleatorio = random.nextInt(90000) + 100000;

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

        System.out.print("Ingrese el numero del tipo de credito (1. Hipotecario, 2. Libre Inversión): ");
        int numero1 = Integer.parseInt(sc.next());

        String productoId;
        String producto;
        double valorSolicitado;
        double tasaAnual;
        int numero2;
        switch (numero1){
            case 1:{
                productoId = "CREHIP-" + Integer.toString(numAleatorio);
                producto = "Credito Hipotecario";
                System.out.print("Ingrese el valor del inmueble: ");
                valorSolicitado = Double.parseDouble(sc.next())*0.7;
                System.out.print("Ingrese el numero del tipo de vivienda(1. VIS, 2. NO VIS): ");
                numero2 = Integer.parseInt(sc.next());
                switch (numero2){
                    case 1:{
                        tasaAnual = 0.178;
                    }break;
                    case 2:{
                        tasaAnual = 0.1785;
                    }break;
                    default: throw new ExcepcionDatoInvalido("Debes ingresar una opción correcta");
                }
            }break;
            case 2: {
                productoId = "CRELIB-" + Integer.toString(numAleatorio);
                producto = "Credito Libre Inversión";
                System.out.print("Ingrese el valor solicitado por el cliente: ");
                valorSolicitado = Double.parseDouble(sc.next());
                tasaAnual = 0.2025;
            }break;
            default: throw new ExcepcionDatoInvalido("Debes ingresar una opción correcta");
        }
        System.out.print("Ingrese el tiempo en años: ");
        int años = Integer.parseInt(sc.next());
        double tasaMensual = tasaAnual/12.0;
        double meses = años*12;
        double cuota = (valorSolicitado * tasaMensual) / (1 - Math.pow(1 + tasaMensual, - meses));

        Path archivoClientes = Paths.get("clientes.txt");
        List<String> lineas = Files.readAllLines(archivoClientes);
        String listaProductos;
        Credito nuevocredito = new Credito(productoId, clienteId, producto, valorSolicitado, cuota, tasaAnual, años, false);
        for (int i = 0; i < lineas.size(); i++) {
            String[] atributos = lineas.get(i).split(",");
            if (atributos[0].equals(clienteId)) {
                if(Double.parseDouble(atributos[8])>cuota){
                    nuevocredito.setAprobado(true);
                    System.out.println("\nEL CREDITO " + productoId + " FUE APROBADO.");
                    ArrayList<String> productos = new ArrayList<>();
                    if (!atributos[9].equals("[]")) {
                        String[] atributoProductos = atributos[9].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
                        for (String atributoProducto : atributoProductos) {
                            productos.add(atributoProducto.trim());
                        }
                    }
                    productos.add(nuevocredito.getProductoId());
                    listaProductos = "["+String.join(";", productos)+"]";
                    atributos[9] = listaProductos;
                    lineas.set(i, String.join(",", atributos));
                    PrintWriter pw = new PrintWriter(new FileWriter("productos.txt", true));
                    String clienteProducto = nuevocredito.toString();
                    pw.print(clienteProducto);
                    pw.close();
                    break;
                } else System.out.println("\nEL CREDITO " + productoId + " NO FUE APROBADO.");
            }
        }
        Files.write(archivoClientes, lineas);
    }

    public static void listarCreditos(){
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
            System.out.println("CREDITOS HIPOTECARIOS / LIBRE INVERSIÓN");
            System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s %s%n", "ID PRODUCTO", "ID CLIENTE", "PRODUCTO", "VALOR SOLICITADO", "VALOR CUOTA MENSUAL", "TASA INTERES E.A", "AÑOS");
            while ((linea = reader.readLine()) != null) {
                String[] atributos = linea.split(",");
                if (atributos[1].equals(clienteId) && (atributos[2].equals("Credito Hipotecario") || atributos[2].equals("Credito Libre Inversión"))) {
                    System.out.printf("%-15s %-25s %-25s %-25s %-25s %-25s %s%n", atributos[0], atributos[1], atributos[2], "$ "+atributos[3], "$ "+atributos[4], Double.toString(Double.parseDouble(atributos[5])*100)+ " %", atributos[6]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes");
            e.printStackTrace();
        }
    }
}
