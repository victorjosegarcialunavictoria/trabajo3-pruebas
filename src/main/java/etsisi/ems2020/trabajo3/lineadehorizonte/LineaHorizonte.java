package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
 

public class LineaHorizonte {
	
	static final PrintWriter OUT = null;
	private Helper help = new Helper();
	private ArrayList <Punto> lineaHorizonte;
	
    /*
     * Constructor sin par�metros
     */
    public LineaHorizonte()
    {
        lineaHorizonte = new ArrayList <Punto>();
    }
            
    /*
     * m�todo que devuelve un objeto de la clase Punto
     */
    public Punto getPunto(int i) {
        return (Punto)this.lineaHorizonte.get(i);
    }
    
    // A�ado un punto a la l�nea del horizonte
    public void addPunto(Punto p)
    {
        lineaHorizonte.add(p);
    }    
    
    // m�todo que borra un punto de la l�nea del horizonte
    public void borrarPunto(int i)
    {
        lineaHorizonte.remove(i);
    }
    
    public int size()
    {
        return lineaHorizonte.size();
    }
    // m�todo que me dice si la l�nea del horizonte est� o no vac�a
    public boolean isEmpty()
    {
        return lineaHorizonte.isEmpty();
    }
   
    /*
      M�todo al que le pasamos una serie de par�metros para poder guardar 
      la linea del horizonte resultante despu�s de haber resuelto el ejercicio
      mediante la t�cnica de divide y vencer�s.
     */
    
    public void guardaLineaHorizonte (String fichero)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(fichero);
            PrintWriter out = new PrintWriter (fileWriter);
            Punto p = new Punto();
         
            for(int i=0; i<this.size(); i++)
            {
                p=getPunto(i);
                out.print(p.getX());
                out.print(" ");
                out.print(p.getY());
                out.println();
            }
            out.close();
        }
        catch(Exception e){}
    }
    
    public void aniadirEdificio(Edificio edificio) {
    	Punto punto1 = new Punto(); // punto donde se guardara en su X la Xi del efificio y en su Y la altura del
		// edificio
    	Punto punto2 = new Punto(); // punto donde se guardara en su X la Xd del efificio y en su Y le pondremos el
		// valor 0
    	punto1.setX(edificio.getXi());
		punto1.setY(edificio.getY()); // guardo la altura
		punto2.setX(edificio.getXd());
		punto2.setY(0);
		
		lineaHorizonte.add(punto1);
		lineaHorizonte.add(punto2);
    }
    
    public LineaHorizonte lineaHorizonteFussion(LineaHorizonte linea1, LineaHorizonte linea2) {
		// en estas variables guardaremos las alturas de los puntos anteriores, en s1y
		// la del s1, en s2y la del s2
		// y en prev guardaremos la previa del segmento anterior introducido
		int altura1 = -1;
		int altura2 = -1;
		int previo = -1;
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
	            previo = help.compararYasignarPuntos(p1, p2, salida, previo);
                linea1.borrarPunto(0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es valido)
                altura1 = p1.getY();   // actualizamos la altura s1y
            }

            else if (p1.getX() > p2.getX()) // si X del s1 es mayor que la X del s2
            {
                previo = help.compararYasignarPuntos(p2, p1, salida, previo);
                altura2 = p2.getY();   // actualizamos la altura s2y
                linea2.borrarPunto(0); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es valido)
            }
            else // si la X del s1 es igual a la X del s2
            {
                salida.addPunto(help.getPuntoMayor(p1, p2));
                previo = help.getPuntoMayor(p1, p2).getY();
                altura1 = p1.getY();   // actualizamos la s1y e s2y
                altura2 = p2.getY();
                linea1.borrarPunto(0); // eliminamos el punto del s1 y del s2
                linea2.borrarPunto(0);
            }
        }
		previo = help.aniadirPuntos(salida, linea1, previo);
	    previo = help.aniadirPuntos(salida, linea2, previo);
		return salida;
	}
    
    public void imprimir (){
    	for(int i=0; i< lineaHorizonte.size(); i++ ){
    		OUT.print(cadena(i));
    	}
    }
    
    public String cadena (int i){
    	Punto p = lineaHorizonte.get(i);
    	int x = p.getX();
    	int y = p.getY();
		return "Punto [x=" + x +", y="+ y +"]";
    }
}
