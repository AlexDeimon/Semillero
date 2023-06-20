package Controladores;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ControladorArchivos {
    static Scanner sc = new Scanner(System.in);
    // obtención de fecha en formato yyyyMMdd
    static LocalDate fechaSistema = LocalDate.now();
    static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static void crearArchivo(String archivo) throws IOException {
        archivo = archivo + '_' + fechaSistema.format(formatoFecha);
        FileWriter fileWriter = new FileWriter(archivo + ".txt");
        fileWriter.close();
        System.out.println("ARCHIVO CREADO EXITOSAMENTE: " + archivo + ".txt\n");
    }

}
