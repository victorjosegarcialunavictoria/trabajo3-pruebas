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
		Punto punto1 = new Punto(); // punto donde se guardara en su X la Xi del efificio y en su Y la altura del
								// edificio
		Punto punto2 = new Punto(); // punto donde se guardara en su X la Xd del efificio y en su Y le pondremos el
								// valor 0
		Edificio edificio = new Edificio();

// Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio. 
		if (posicionIzq == posicionDrxa) {
			edificio = this.getEdificio(posicionIzq); // Obtenemos el único edificio y lo guardo en b
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
			int medio = (posicionIzq + posicionDrxa) / 2;

			LineaHorizonte s1 = this.crearLineaHorizonte(posicionIzq, medio);
			LineaHorizonte s2 = this.crearLineaHorizonte(medio + 1, posicionDrxa);
			linea = lineaHorizonteFussion(s1, s2);
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
	public LineaHorizonte lineaHorizonteFussion(LineaHorizonte linea1, LineaHorizonte linea2) {
		// en estas variables guardaremos las alturas de los puntos anteriores, en s1y
		// la del s1, en s2y la del s2
		// y en prev guardaremos la previa del segmento anterior introducido
		int altura1 = -1;
		int altura2 = -1;
		int prev = -1;
		LineaHorizonte salida = new LineaHorizonte(); // LineaHorizonte de salida
			
		Punto p1 = new Punto(); 
		Punto p2 = new Punto();

		
		OUT.println("==== S1 ====");
		linea1.imprimir();
		OUT.println("==== S2 ====");
		linea2.imprimir();
		OUT.println("\n");

		// Mientras tengamos elementos en linea1 y en linea2
		while ((!linea1.isEmpty()) && (!linea2.isEmpty())) 
        {
            p1 = linea1.getPunto(0); // guardamos el primer elemento de s1
            p2 = linea2.getPunto(0); // guardamos el primer elemento de s2

            if (p1.getX() < p2.getX()) // si X del s1 es menor que la X del s2
            {
                juntarCosas(p1, p2, salida, prev);
                linea1.borrarPunto(0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es valido)
                altura1 = p1.getY();   // actualizamos la altura s1y
            }

            else if (p1.getX() > p2.getX()) // si X del s1 es mayor que la X del s2
            {
                juntarCosas(p2, p1, salida, prev);
                altura2 = p2.getY();   // actualizamos la altura s2y
                linea2.borrarPunto(0); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es valido)
            }
            else // si la X del s1 es igual a la X del s2
            {
                salida.addPunto(getPuntoMayor(p1, p2));
                prev = getPuntoMayor(p1, p2).getY();
                altura1 = p1.getY();   // actualizamos la s1y e s2y
                altura2 = p2.getY();
                linea1.borrarPunto(0); // eliminamos el punto del s1 y del s2
                linea2.borrarPunto(0);
            }
        }
		comoQuieras(salida, linea1, prev);
	    comoQuieras(salida, linea2, prev);
		return salida;
	}
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
	
	/**
    Función auxiliar para fusionar 2 LineaHorizonte en una final/definitiva
     */
     public void juntarCosas(Punto puntoMayor, Punto puntoMenor, LineaHorizonte lineaDefinitiva, int previo){
        Punto pAux = new Punto();
        pAux.setX(puntoMayor.getX());
        pAux.setY(Math.max(puntoMayor.getY(), puntoMenor.getY()));
        if (pAux.getY()!=previo){ // si este maximo no es igual al del segmento anterior
            lineaDefinitiva.addPunto(pAux); // añadimos el punto al LineaHorizonte definitivo
            previo = Math.max(puntoMayor.getY(), puntoMenor.getY());    // actualizamos prev
        }
    }

    /**
    Función que recibe como parámetros 2 puntos y devuelve el de mayor altura
     */
	public Punto getPuntoMayor(Punto punto1, Punto punto2) {
		if (punto1.getY() > punto2.getY())
			return punto1;
		else
			return punto2;
	}
	
	public void comoQuieras(LineaHorizonte lineaDefinitiva, LineaHorizonte linea, int previo) {
		while (!linea.isEmpty()) {

			Punto pAux = linea.getPunto(0); // guardamos en paux el primer punto

			if (pAux.getY() != previo) // si paux no tiene la misma altura del segmento previo
			{
				lineaDefinitiva.addPunto(pAux); // lo añadimos al LineaHorizonte de salida
				previo = pAux.getY(); // y actualizamos prev
			}
			linea.borrarPunto(0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es
									// valido)
		}
	}
}
