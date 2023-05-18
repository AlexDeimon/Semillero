/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package archivos;

import java.io.*;

/**
 *
 * @author DIEGO
 */
public class Archivos {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            FileWriter fl = new FileWriter("test.txt");
            PrintWriter pw = new PrintWriter(fl);

            for(int i = 0; i < 10; i++) {
              pw.println(i + 1);
              System.out.println(i + 1);
            }

            pw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
