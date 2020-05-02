package etsisi.ems2020.trabajo3.lineadehorizonte;

public class Helper {
	
	public Helper() {
		
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
	
	public int aniadirPuntos(LineaHorizonte lineaDefinitiva, LineaHorizonte linea, int previo) { //Revisar
		int previoAux = previo;
		while (!linea.isEmpty()) {
			Punto pAux = linea.getPunto(0); // guardamos en paux el primer punto
			
			if (pAux.getY() != previoAux) // si paux no tiene la misma altura del segmento previo
			{
				lineaDefinitiva.addPunto(pAux); // lo añadimos al LineaHorizonte de salida
				previoAux = pAux.getY(); // y actualizamos prev
			}
			linea.borrarPunto(0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es
									// valido)
		}
		return previoAux;
	}
    
	/**
    Función auxiliar para fusionar 2 LineaHorizonte
     */
     public int compararYasignarPuntos(Punto puntoMayor, Punto puntoMenor, LineaHorizonte lineaDefinitiva, int previo){
        Punto pAux = new Punto();
        
        pAux.setX(puntoMenor.getX());
        pAux.setY(Math.max(puntoMayor.getY(), puntoMenor.getY()));
        if (pAux.getY()!=previo){ // si este maximo no es igual al del segmento anterior
            lineaDefinitiva.addPunto(pAux); // añadimos el punto al LineaHorizonte definitivo
            previo = pAux.getY();    // actualizamos prev
        }
        return previo;
    }

}
