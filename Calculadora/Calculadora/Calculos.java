package Calculadora;
import java.io.*;
public class Calculos {
	public static void main(String ars[]){
		Cientifica operacion = new Cientifica();
	//objeto clase científica
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Ingresa un numero:");
			String n1 = br.readLine();
			System.out.println("Ingresa otro numero:");
			String n2 = br.readLine();
	
			double a = Double.parseDouble(n1);
			double b = Double.parseDouble(n2);
	
			//Llamamos a los métodos de la clase
	
			System.out.println("La suma es:" + operacion.sumar(a,b));
			System.out.println("La resta es:" + operacion.restar(a,b));
			System.out.println("El producto es:" + operacion.multiplicar(a,b));
			System.out.println("El cociente es:" + operacion.dividir(a,b));
			System.out.println("La potencia es:" + operacion.potencia(a,b));
	
		}
	
	
		catch(Exception e){
	
	
			System.out.println("Error en los datos");
	
	
		}
	
	}
}
