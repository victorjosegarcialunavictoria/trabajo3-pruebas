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
	
	/**
	 * Obtener una linea horizonte
	 */
	public LineaHorizonte getLineaHorizonte() {
		// pi y pd, representan los edificios de la izquierda y de la derecha.
		final int edificioIzq = 0;
		final int edificioDcha = edificios.size() - 1;
		return crearLineaHorizonte(edificioIzq, edificioDcha);
	}
	
	/**
	 * Encargado de crear una linea horizonte con los valores pasados como parámetros
	 * @param posicionIzq
	 * @param posicionDrxa
	 * @return una lineaHorizonte
	 */
	public LineaHorizonte crearLineaHorizonte(final int posicionIzq, final int posicionDrxa) {
		LineaHorizonte linea = new LineaHorizonte(); // LineaHorizonte de salida
		
		Edificio edificio = new Edificio();

// Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio. 
		if (posicionIzq == posicionDrxa) {
			edificio = this.getEdificio(posicionIzq); // Obtenemos el único edificio y lo guardo en b
// En cada punto guardamos la coordenada X y la altura.
			 // como el edificio se compone de 3 variables, en la Y de p2 le añadiremos un 0
// Añado los puntos a la línea del horizonte
			linea.aniadirEdificio(edificio);
		} else {
// Edificio mitad
			final int medio = (posicionIzq + posicionDrxa) / 2;

			final LineaHorizonte horizonte1 = this.crearLineaHorizonte(posicionIzq, medio);
			final LineaHorizonte horizonte2 = this.crearLineaHorizonte(medio + 1, posicionDrxa);
			linea = linea.lineaHorizonteFussion(horizonte1, horizonte2);
		}
		return linea;
	}

	/**
	 * Función encargada de fusionar los dos LineaHorizonte obtenidos por la técnica
	 * divide y vencerás. Es una función muy compleja ya que es la encargada de
	 * decidir si un edificio solapa a otro, si hay edificios contiguos, etc. y
	 * solucionar dichos problemas para que el LineaHorizonte calculado sea el
	 * correcto.
	 */
	
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
