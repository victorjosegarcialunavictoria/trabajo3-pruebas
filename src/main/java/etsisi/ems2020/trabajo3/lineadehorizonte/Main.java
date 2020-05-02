package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
	
	static final PrintWriter OUT = null;

	public static void main(String[] args) {
		
		
		/*
		 Empezamos a ejecutar el c�digo para intentar hacer el ejercicio
		 que nos piden, calcular la l�nea del horizonte de una ciudad.
		 */
        Ciudad c = new Ciudad();
        c.cargarEdificios("ciudad1.txt");
        
        // Creamos l�nea del horizonte
        LineaHorizonte linea = new LineaHorizonte();
        linea = linea.getLineaHorizonte(c);
        //Guardamos la linea del horizonte
        
        linea.guardaLineaHorizonte("salida.txt");
        OUT.println("-- Proceso finalizado Correctamente --");
        Punto  p2 = new Punto(5,6);
        OUT.println(p2);
	}

}
