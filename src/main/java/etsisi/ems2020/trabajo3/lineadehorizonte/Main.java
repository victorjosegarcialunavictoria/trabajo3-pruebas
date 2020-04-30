package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.PrintWriter;

public class Main {
	
	/** 
	 * Creacion de variables miembro
	 */
	private static final PrintWriter OUT = null;

	/**
	 * Creacion de metodo principal
	 */
	public static void main(final String[] args) {
		/*
		 Empezamos a ejecutar el c�digo para intentar hacer el ejercicio
		 que nos piden, calcular la l�nea del horizonte de una ciudad.
		 */
        final Ciudad ciudad = new Ciudad();
        ciudad.cargarEdificios("ciudad1.txt");
        
        // Creamos linea del horizonte
        LineaHorizonte linea = new LineaHorizonte();
        linea = ciudad.getLineaHorizonte();
        
        //Guardamos la linea del horizonte        
        linea.guardaLineaHorizonte("salida.txt");
        OUT.println("-- Proceso finalizado Correctamente --");
        final Punto punto = new Punto(5,6);
        OUT.println(punto);
	}

}
