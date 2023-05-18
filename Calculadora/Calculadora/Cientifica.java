package Calculadora;

public class Cientifica extends Calculadora{
// No tiene variables instancia

// Constructor que invoca al constructor de calculadora
	public Cientifica(){
		super(); //Invoca al constructor de la clase superior
	}

//Métodos que nos faltan

	public double potencia(double a, double b){
		return Math.pow(a,b);
	}

}
