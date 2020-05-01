package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 Clase fundamental.
 Sirve para hacer la lectura del fichero de entrada que contiene los datos de como
 están situados los edificios en el fichero de entrada. xi, xd, h, siendo. Siendo
 xi la coordenada en X origen del edificio iésimo, xd la coordenada final en X, y h la altura del edificio.
 
 */
public class Ciudad {
	
	static final PrintWriter OUT = null;

	private ArrayList<Edificio> ciudad;

	public Ciudad() {

		/*
		 * Generamos una ciudad de manera aleatoria para hacer pruebas.
		 */
		ciudad = new ArrayList<Edificio>();
		int n = 5;
		int i = 0;
		int xi, y, xd;
		for (i = 0; i < n; i++) {
			xi = (int) (Math.random() * 100);
			y = (int) (Math.random() * 100);
			xd = (int) (xi + (Math.random() * 100));
			this.addEdificio(xi, y, xd);
		}

		ciudad = new ArrayList<Edificio>();
	}

	public Edificio getEdificio(int posicion) {
		return (Edificio) this.ciudad.get(posicion);
	}

	public void addEdificio(int coordOrigen, int altura, int coordFinal) {
		ciudad.add(new Edificio(coordOrigen, altura, coordFinal));
	}

	public void removeEdificio(int posicion) {
		ciudad.remove(posicion);
	}

	public int size() {
		return ciudad.size();
	}

	public LineaHorizonte getLineaHorizonte() {
		// pi y pd, representan los edificios de la izquierda y de la derecha.
		int pi = 0;
		int pd = ciudad.size() - 1;
		return crearLineaHorizonte(pi, pd);
	}

	public LineaHorizonte crearLineaHorizonte(int posicionIzq, int posicionDrxa) {
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
			int medio = (posicionIzq + posicionDrxa) / 2;

			LineaHorizonte s1 = this.crearLineaHorizonte(posicionIzq, medio);
			LineaHorizonte s2 = this.crearLineaHorizonte(medio + 1, posicionDrxa);
			linea = linea.lineaHorizonteFussion(s1, s2);
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

	public void cargarEdificios(String fichero) {

		try {
			Scanner sr = new Scanner(new File(fichero));

			while (sr.hasNext()) {
				this.addEdificio(sr.nextInt(), sr.nextInt(), sr.nextInt());
			}	
			sr.close();
		} catch (Exception e) {
		}

	}

	public void metodoRandom(int numeroEdificios) {
		for (int i = 0; i < numeroEdificios; i++) {
			final int coordOrigen = (int) (Math.random() * 100);
			final int altura = coordOrigen;
			final int coordFinal = (int) (coordOrigen + (Math.random() * 100));
			this.addEdificio(coordOrigen, altura, coordFinal);
		}
	}
	
	

   
	
	
}
