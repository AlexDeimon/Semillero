package Controladores;
import Excepciones.ExcepcionArchivoInvalido;
import Excepciones.ExcepcionCuentaInvalida;

import Modelos.CuentaOrigen;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ControladorCuentaOrigen {
    static Scanner sc = new Scanner(System.in);
    static String patron = "\\d{9}"; // Expresión regular para cadena de 9 numeros

    // obtención de fecha en formato yyyyMMdd
    static LocalDate fechaSistema = LocalDate.now();
    static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyyMMdd");

    // nombres de archivos
    static String archivo = "ASOCIACIONES_"+fechaSistema.format(formatoFecha)+".txt";
    static String archivo2 = "TRANSACCIONES_"+fechaSistema.format(formatoFecha)+".txt";

    public static void registrar(String nit) {
        Formatter formatter = new Formatter(); //formato para impresión en el archivo txt alineado
        System.out.print("Digite el numero de la cuenta origen: ");
        String numeroCuentaOrigen = sc.next();
        if (Pattern.matches(patron, numeroCuentaOrigen)) {
            CuentaOrigen nuevaCuenta = new CuentaOrigen(numeroCuentaOrigen);
            // revisar que exista o no el archivo ASOCIACIONES
            boolean archivoExiste = new File(archivo).exists();
            try {
                if (!archivoExiste) {
                    // se crean los 2 archivos (ASOCIACIONES y TRASNSACCIONES) y se escribre el header con el numero de cuenta Origen y el MD5 generado
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter(archivo));
                    BufferedWriter writer2 = new BufferedWriter(new FileWriter(archivo2));
                    ControladorArchivos.crearArchivo("ASOCIACIONES");
                    ControladorArchivos.crearArchivo("TRANSACCIONES");
                    String cadena = "H " + nit + " " + nuevaCuenta.getNumeroCuentaOrigen();
                    String MD5 = generarMD5(cadena);
                    String header = formatter.format("%-21s %s", cadena, MD5).toString();
                    writer1.write(header);
                    writer2.write(header);
                    writer1.close();
                    writer2.close();
                } else {
                    // se revisa que el nit y el numero de cuenta origen digitados en Programa, sean igual al que esta en el Header
                    BufferedReader reader = new BufferedReader(new FileReader(archivo));
                    if (!reader.readLine().startsWith("H " + nit + " " + nuevaCuenta.getNumeroCuentaOrigen()))
                        throw new ExcepcionCuentaInvalida("REVISAR LOS DATOS YA QUE NO CORRESPONDEN A LOS REGISTRADOS");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } else throw new ExcepcionCuentaInvalida("EL NUMERO DE CUENTA DEBE TENER EXACTAMENTE 9 CARACTERES NUMERICOS");
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
