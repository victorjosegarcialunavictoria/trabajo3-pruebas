package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 Clase fundamental.
 Contiene los datos de como están situados los edificios 
 en el fichero de entrada. xi, xd, h, siendo
 xi la coordenada en X origen del edificio iésimo, 
 xd la coordenada final en X, 
 y h la altura del edificio.
 */
public class Ciudad {
	
	/**
	 * Variable creada para imprimir por pantalla
	 */
	private static final PrintWriter OUT = null;
	
	/**
	 * ArrayList que contiene todos los edificios que conforman la ciudad
	 */
	private static ArrayList<Edificio> edificios;
	
	/**
	 * Define el máximo número de edificios que va a tener la ciudad
	 */
	private static final int NUM_EDIFICIOS = 5;
	
	/**
	 * Contructor de la clase
	 */
	public Ciudad() {

		/*
		 * Generamos una ciudad de manera aleatoria para hacer pruebas.
		 */
		edificios = new ArrayList<Edificio>();
		try {
			metodoRandom(NUM_EDIFICIOS);
		}catch (NullPointerException e) {
			OUT.println(e.getMessage());
		}

		
	}
	
	/**
	 * 
	 * @param posicion
	 * @return un Edificio
	 * Devuelve el edificio de la posicion solicitada
	 */

	public Edificio getEdificio(final int posicion) {
		return (Edificio) this.edificios.get(posicion);
	}
	
	/**
	 * Añade un edificio con las coordenadas y la altura pasadas por parámetro
	 * @param coordOrigen
	 * @param altura
	 * @param coordFinal
	 */

	public void addEdificio(final int coordOrigen, final int altura, final int coordFinal) {
		edificios.add(new Edificio(coordOrigen, altura, coordFinal));
	}
	
	/**
	 * Elimina el edificio de la posicion que se pasa por parametro
	 * @param posicion
	 */

	public void removeEdificio(final int posicion) {
		edificios.remove(posicion);
	}
	
	/**
	 * 
	 * @return el tamaño de la ciudad
	 * Devuelve el numero de edificios
	 */

	public int size() {
		return edificios.size();
	}
	

	/*
	 * Método que carga los edificios que me pasan en el archivo cuyo nombre se
	 * encuentra en "fichero". El formato del fichero nos lo ha dado el profesor en
	 * la clase del 9/3/2020, pocos días antes del estado de alarma.
	 */

	public void cargarEdificios(final String fichero) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fichero));

			while (scanner.hasNext()) {
				this.addEdificio(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			}	
			
		} catch (FileNotFoundException e) {
			OUT.println(e.getMessage());
		}
		finally {
			scanner.close();
		}

	}
	
	/**
	 * 
	 * @param numeroEdificios Numero de edificios de ciudad
	 * Crea los edificios de la ciudad y los añade a la ciudad
	 */

	public void metodoRandom(final int numeroEdificios) {
		for (int i = 0; i < numeroEdificios; i++) {
			final int coordOrigen = (int) (Math.random() * 100);
			final int altura = coordOrigen;
			final int coordFinal = (int) (coordOrigen + (Math.random() * 100));
			this.addEdificio(coordOrigen, altura, coordFinal);
		}
	}
	
}
