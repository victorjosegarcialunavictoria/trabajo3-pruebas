package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contiene los datos de como están situados los edificios
 * en el fichero de entrada. xi, xd, h, siendo. Siendo
 * xi la coordenada en X origen del edificio iésimo, 
 * xd la coordenada final en X, y h la altura del edificio.
 */
public class Ciudad {
	
	/**
	 * Creacion variable de PrintWriter
	 * */
	private static final PrintWriter OUT = null;
	/**
	 * Creacion de ciudad con una lista de Edificios
	 * */
	private static final ArrayList<Edificio> EDIFICIOS = new ArrayList<Edificio>();

	/**
	 * Constructor
	 * */
	public Ciudad() {

		/*
		 * Generamos una ciudad de manera aleatoria para hacer pruebas.
		 */
		
		int coordOrigen;
		int altura;
		int coordFinal;
		
		for (int i = 0; i < 5; i++) {
			coordOrigen = (int) (Math.random() * 100);
			altura = (int) (Math.random() * 100);
			coordFinal = (int) (coordOrigen + (Math.random() * 100));
			this.addEdificio(coordOrigen, altura, coordFinal);
		}
	}

	/** 
	 * Devuelve un edificio
	 * @param int i posicion del edificio
	 */
	public Edificio getEdificio(final int posicion) {
		return (Edificio) EDIFICIOS.get(posicion);
	}

	/**
	 * Añade edificio en ciudad
	 */
	public void addEdificio(final int coordOrigen, final int altura, final int coordFinal) {
		final Edificio edificio = new Edificio(coordOrigen, altura, coordFinal);
		EDIFICIOS.add(edificio);
	}

	/**
	 * Elimina edificio en ciudad
	 */
	public void removeEdificio(final int posicion) {
		EDIFICIOS.remove(posicion);
	}

	/** 
	 * Devuelve el tamaño de la ciudad
	 */
	public int size() {
		return EDIFICIOS.size();
	}

	/**
	 * Devuelve linea de horizonte
	 * */
	public LineaHorizonte getLineaHorizonte() {
		return crearLineaHorizonte(0, EDIFICIOS.size() - 1);
	}

	/** 
	 * Se crea una linea con los edificios de izq y drxa
	 * @param int edificioIzq posicion del edificio de la izq
	 * @param int edificioDrxa posicion del edificio de la drxa
	 * @return devuelve una linea*/
	public LineaHorizonte crearLineaHorizonte(final int edificioIzq, final int edificioDrxa) {

		LineaHorizonte linea = new LineaHorizonte(); // LineaHorizonte de salida
		final Punto punto1 = new Punto(); // punto donde se guardara en su X la Xi del efificio y en su Y la altura del
								// edificio
		final Punto punto2 = new Punto(); // punto donde se guardara en su X la Xd del efificio y en su Y le pondremos el
								// valor 0
		Edificio edificio = new Edificio();

		// Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio.
		if (edificioIzq == edificioDrxa) {
			edificio = this.getEdificio(edificioIzq); // Obtenemos el único edificio y lo guardo en b
			// En cada punto guardamos la coordenada X y la altura.
			punto1.setX(edificio.getXi());
			punto1.setY(edificio.getY()); // guardo la altura
			punto2.setX(edificio.getXd());
			punto2.setY(0); // como el edificio se compone de 3 variables, en la Y de p2 le añadiremos un 0
			// Añado los puntos a la línea del horizonte
			linea.addPunto(punto1);
			linea.addPunto(punto2);
		} else {
			// Edificio mitad
			final int medio = (edificioIzq + edificioDrxa) / 2;

			final LineaHorizonte linea1 = this.crearLineaHorizonte(edificioIzq, medio);
			final LineaHorizonte linea2 = this.crearLineaHorizonte(medio + 1, edificioDrxa);
			linea = lineaHorizonteFussion(linea1, linea2);
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
	public LineaHorizonte lineaHorizonteFussion(final LineaHorizonte linea1, final LineaHorizonte linea2) {
		// en estas variables guardaremos las alturas de los puntos anteriores, en s1y
		// la del s1, en s2y la del s2
		// y en prev guardaremos la previa del segmento anterior introducido
		int s1y = -1;
		int s2y = -1;
		int prev = -1;
		final LineaHorizonte salida = new LineaHorizonte(); // LineaHorizonte de salida
			
		Punto punto1 = new Punto(); 
		Punto punto2 = new Punto();
		Punto paux = new Punto();
		
		OUT.println("==== S1 ====");
		linea1.imprimir();
		OUT.println("==== S2 ====");
		linea2.imprimir();
		OUT.println("\n");

		// Mientras tengamos elementos en s1 y en s2
		while (!linea1.isEmpty() && !linea2.isEmpty()) {		
			punto1 = linea1.getPunto(0); // guardamos el primer elemento de s1
			punto2 = linea2.getPunto(0); // guardamos el primer elemento de s2			
		}
		
		if (punto1.getX() < punto2.getX()) // si X del s1 es menor que la X del s2
		{
			guardarPunto(punto1, paux, s2y);											
			esValido(linea1, paux, prev, salida);
			s1y = punto1.getY(); // actualizamos la altura s1y
											// valido)
		} else if (punto1.getX() > punto2.getX()) // si X del s1 es mayor que la X del s2
		{
			guardarPunto(punto2, paux, s1y);
			esValido(linea2, paux, prev, salida);
			s2y = punto2.getY(); // actualizamos la altura s2y
			
		} else // si la X del s1 es igual a la X del s2
		{
			if (punto1.getY() > punto2.getY() && esMaximo(punto1, prev)) // guardaremos aquel punto que tenga la altura mas
																		// alta
			{
				guardarPuntoAlto(punto1, salida, prev);
			}
			if (punto1.getY() <= punto2.getY() && esMaximo(punto2, prev)) {
				guardarPuntoAlto(punto2, salida, prev);
			}
			s1y = punto1.getY(); // actualizamos la s1y e s2y
			s2y = punto2.getY();
			linea1.borrarPunto(0); // eliminamos el punto del s1 y del s2
			linea2.borrarPunto(0);
		}
		
		verificarElementos(linea1, paux, prev, salida);
		verificarElementos(linea2, paux, prev, salida);
		
		return salida;
	}
	
	/**
	 * Método que carga los edificios que me pasan en el archivo cuyo nombre se
	 * encuentra en "fichero". El formato del fichero nos lo ha dado el profesor en
	 * la clase del 9/3/2020, pocos días antes del estado de alarma.
	 */
	public void cargarEdificios(final String fichero) {


		try {
			
			int cordOrigen;
			int altura;
			int cordFinal;
			final Scanner scan = new Scanner(new File(fichero));

			while (scan.hasNext()) {
				cordOrigen = scan.nextInt();
				cordFinal = scan.nextInt();
				altura = scan.nextInt();

				this.addEdificio(cordOrigen, altura, cordFinal);
			}	
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creacion de una ciudad aleatoria
	 * @param numEdificios es el numero de edificios de la ciudad*/
	public void metodoRandom(final int numEdificios) {
		int coordOrigen;
		int altura;
		int coordFinal;
		for (int i = 0; i < numEdificios; i++) {
			coordOrigen = (int) (Math.random() * 100);
			altura = (int) (Math.random() * 100);
			coordFinal = (int) (coordOrigen + (Math.random() * 100));
			this.addEdificio(coordOrigen, altura, coordFinal);
		}
	}
	
	public boolean esMaximo(Punto punto, int prev) 
	{
		return punto.getY() != prev;
	}
	
	public void verificarElementos(LineaHorizonte linea, Punto punto, int prev, LineaHorizonte salida) {
		while (!linea.isEmpty()) // si aun nos quedan elementos en el s1
		{
			punto = linea.getPunto(0); // guardamos en paux el primer punto
			esValido(linea, punto, prev, salida);
			
		}
	}
	
	public void esValido(LineaHorizonte linea, Punto punto, int prev, LineaHorizonte salida) {
		if (esMaximo(punto, prev)) // si paux no tiene la misma altura del segmento previo
		{
			salida.addPunto(punto); // lo añadimos al LineaHorizonte de salida
			prev = punto.getY(); // y actualizamos prev
		}
		linea.borrarPunto(0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es
								// valido)
	}
	
	public void guardarPunto(Punto punto1, Punto paux, int alturaPrev) {
		paux.setX(punto1.getX()); // guardamos en paux esa X
		paux.setY(Math.max(punto1.getY(), alturaPrev)); // y hacemos que el maximo entre la Y del s1 y la altura previa del
												// s2 sea la altura Y de paux	
	}
	
	public void guardarPuntoAlto(final Punto punto, final LineaHorizonte salida, int prev) {
		salida.addPunto(punto);
		prev = punto.getY();
	}
	
	public boolean esMasAlto(Punto punto1, Punto punto2, int prev, char coordenada) {
		if(coordenada == 'Y')
			return (punto1.getY() > punto2.getY()) && esMaximo(punto2, prev);
		else 
			return false;
		
	}
}
