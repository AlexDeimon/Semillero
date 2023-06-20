package Controladores;
import Excepciones.ExcepcionArchivoInvalido;
import Excepciones.ExcepcionCuentaInvalida;
import Excepciones.ExcepcionCedulaInvalida;

import Modelos.CuentaDestino;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ControladorCuentaDestino {
    static Scanner sc = new Scanner(System.in);

    // obtención de fecha en formato yyyyMMdd
    static LocalDate fechaSistema = LocalDate.now();
    static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyyMMdd");

    // nombre del archivo donde se registrará la asociación de la cuenta
    static String archivo = "ASOCIACIONES_"+fechaSistema.format(formatoFecha)+".txt";

    static String patron = "\\d{9}"; // Expresión regular para cadena de 9 numeros
    static String patron2 = "^[0-9]{8,10}$";// Expresión regular para cadena de entre 8 a 10 numeros

    public static void asociarCuenta() throws IOException {
        int contador = -1; //numero de registros detalle. Inicio en -1 para no contar el Header

        //formatos para impresión en el archivo txt alineado
        Formatter formatter = new Formatter();
        Formatter formatter2 = new Formatter();

        try {
            System.out.print("Ingrese la cédula: ");
            String cedula = sc.nextLine();
            if(!Pattern.matches(patron2, cedula)) throw new ExcepcionCedulaInvalida("LA CEDULA DEBE TENER ENTRE 8 Y 10 CARACTERES NUMERICOS");
            System.out.print("Ingrese el número de cuenta destino: ");
            String numeroCuentaDestino = sc.nextLine();
            CuentaDestino nuevaCuenta;
            if (Pattern.matches(patron, numeroCuentaDestino)) nuevaCuenta = new CuentaDestino(numeroCuentaDestino, cedula);
            else throw new ExcepcionCuentaInvalida("EL NUMERO DE CUENTA DEBE TENER EXACTAMENTE 9 CARACTERES NUMERICOS");
            // se lee el archivo linea a linea
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null ) {
                if(linea.contains(nuevaCuenta.getNumeroCuentaDestino()) && linea.startsWith("D")) throw new ExcepcionCedulaInvalida("EL NUMERO DE CUENTA YA ESTA ASOCIADO");
                if(linea.startsWith("D "+ nuevaCuenta.getCedula())) throw new ExcepcionCuentaInvalida("LA CEDULA YA TIENE UN NUMERO DE CUENTA ASOCIADO");
                if(!linea.startsWith("T")){ // no contar el Trailer para no repetirlo en el contenido
                    contenido.append(linea);
                    contenido.append(System.lineSeparator());
                    contador++;
                }
            }

            reader.close();
            // se agrega el registro de la asociación al contenido junto con el MD5
            String cadena = formatter.format("D %-10s %-9s", nuevaCuenta.getCedula(), nuevaCuenta.getNumeroCuentaDestino()).toString();
            String detalle = cadena + " " + generarMD5(cadena);
            contenido.append(detalle).append(System.lineSeparator());
            contador++;
            // se agrega el trailer al contenido
            String cadena2 = "T REGISTROS:" + contador;
            String MD52 = generarMD5(cadena2);
            String trailer = formatter2.format("%-22s %s", cadena2, MD52).toString();
            contenido.append(trailer);
            // se escribe el contenido en el archivo reemplazando lo que contenia anteriormente (evita borrar el archivo)
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(archivo));
            writer2.write(contenido.toString());
            writer2.close();
            System.out.println("SE HA ASOCIADO LA CUENTA CON EXITO!");
        } catch (IOException e) {
            System.out.println("Error al registrar los datos: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    private static String generarMD5(String texto) throws NoSuchAlgorithmException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("CONFIGURACION.txt"));
            String linea;
            String llave = null;
            // leer el archivo linea a linea y extrae la llave MD5 con los valores usando clave/valor
            while ((linea = reader.readLine()) != null) {
                // evitar espacios en blanco
                if (linea.contains(": ")) throw new ExcepcionArchivoInvalido("FORMATO DE ARCHIVO INCORRECTO. VERIFIQUE EL ARCHIVO CONFIGURACION.TXT");
                else {
                    String[] partes = linea.split(":");
                    String clave = partes[0].trim();
                    String valor = partes[1].trim();
                    if (clave.equals("llaveMD5")) llave = valor;
                }
            }
            if (llave!= null) {
                String cadena = texto + llave;
                // Obtener una instancia del algoritmo de hash MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                // Actualizar el mensaje de hash con la representación en bytes del texto
                md.update(cadena.getBytes());
                // Obtener el valor de hash resultante
                byte[] digest = md.digest();
                // Crear un StringBuilder para construir la representación hexadecimal del hash
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    // Convertir cada byte en una cadena hexadecimal y agregarlo al StringBuilder
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } else throw new ExcepcionArchivoInvalido("EL ARCHIVO NO CONTIENE LOS DATOS NECESARIOS. VERIFIQUE EL ARCHIVO CONFIGURACION.TXT");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
