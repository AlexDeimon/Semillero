package Controladores;
import Excepciones.ExcepcionArchivoInvalido;
import Excepciones.ExcepcionCuentaInvalida;
import Excepciones.ExcepcionValorInvalido;

import Modelos.Transaccion;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ControladorTransaccion {
    static Scanner sc = new Scanner(System.in);

    // obtención de fecha en formato yyyyMMdd
    static LocalDate fechaSistema = LocalDate.now();
    static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyyMMdd");

    // nombres del archivos
    static String archivoT = "TRANSACCIONES_"+fechaSistema.format(formatoFecha)+".txt";
    static String archivoA = "ASOCIACIONES_"+fechaSistema.format(formatoFecha)+".txt";

    static String patron = "\\d{9}"; // Expresión regular para cadena de 9 numeros
    static String patron2 = "^[0-9]{5,9}$"; // Expresión regular para cadena de entre 5 a 9 numeros
    public static void revisarAsociacion() {
        try {
            System.out.print("Ingrese el número de cuenta destino: ");
            String numeroCuentaDestino = sc.nextLine();
            System.out.print("Ingrese el valor del pago de nomina: ");
            int valor = Integer.parseInt(sc.nextLine());
            Transaccion nuevaTransaccion;
            if (!Pattern.matches(patron, numeroCuentaDestino)) throw new ExcepcionCuentaInvalida("EL NUMERO DE CUENTA DEBE TENER EXACTAMENTE 9 CARACTERES NUMERICOS");
            else if(!Pattern.matches(patron2, String.valueOf(valor))) throw new ExcepcionValorInvalido("EL VALOR DE PAGO DEBE SER MAYOR A 4 DIGITOS Y MENOR DE 10 DIGITOS");
            else nuevaTransaccion = new Transaccion(numeroCuentaDestino, valor);
            // se lee el archivo ASOCIACIONES para revisar que la cuenta este asociada
            BufferedReader readerA = new BufferedReader(new FileReader(archivoA));
            String lineaA;
            while ((lineaA = readerA.readLine()) != null ) {
                if(lineaA.contains(numeroCuentaDestino) && lineaA.startsWith("D")) {
                    registrarTransaccion(nuevaTransaccion.getNumeroCuentaDestino(), nuevaTransaccion.getValor());break;
                }

            }
            readerA.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void registrarTransaccion(String numeroCuentaDestino, int valor) throws IOException {
        int contador = -1; //numero de registros detalle. Inicio en -1 para no contar el Header

        //formatos para impresión en el archivo txt alineado
        Formatter formatter = new Formatter();
        Formatter formatter2 = new Formatter();

        try {
            // se lee el archivo TRASNSACCIONES linea a linea
            BufferedReader readerT = new BufferedReader(new FileReader(archivoT));
            StringBuilder contenido = new StringBuilder();
            String lineaT;

            while ((lineaT = readerT.readLine()) != null ) {
                if(lineaT.startsWith("D "+ numeroCuentaDestino)) throw new ExcepcionCuentaInvalida("YA SE REGISTRO EL PAGO A ESTE NUMERO DE CUENTA");
                if(!lineaT.startsWith("T")){ // no contar el Trailer para no repetirlo en el contenido
                    contenido.append(lineaT);
                    contenido.append(System.lineSeparator());
                    contador++;
                }
            }

            readerT.close();
            // se agrega el registro de la transacción al contenido
            String cadena = formatter.format("D %-10s %-9s", numeroCuentaDestino, valor).toString();
            String detalle = cadena + " " + generarMD5(cadena);
            contenido.append(detalle).append(System.lineSeparator());
            contador++;
            // se agrega el trailer al contenido
            String cadena2 = "T REGISTROS:" + contador;
            String MD52 = generarMD5(cadena2);
            String trailer = formatter2.format("%-22s %s", cadena2, MD52).toString();
            contenido.append(trailer);
            // se escribe el contenido en el archivo reemplazando lo que contenia anteriormente (evita borrar el archivo)
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(archivoT));
            writer2.write(contenido.toString());
            writer2.close();
            System.out.println("SE HA REALIZADO LA TRANSACCIÓN DE PAGO DE NOMINA!");
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
