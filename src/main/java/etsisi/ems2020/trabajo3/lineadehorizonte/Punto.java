package etsisi.ems2020.trabajo3.lineadehorizonte;

/**
 * @author Juan Manuel
 * Clase para definir un punto sobre el eje
 * cartesiano de coordendas
 */
public class Punto {
	
	/**
	 * Coordenada X del punto
	 */
	private transient int coordX;
	/**
	 * Coordenada Y del punto
	 */
    private transient int coordY;

    /**
     * Constructor sin par�metros de un punto en concreto
     */
    public Punto() {
        this.coordX = 0;
        this.coordY = 0;
    }
    
    /**
     * Constructos con par�metros de un punto
     * @param x
     * @param y
     */
    public Punto(final int coordX, final int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }
    /**
     * 
     * Get de la coordenada X
     */
    public int getX() {
        return coordX;
    }
    
    /**
     * 
     * Set de la coordenada X
     */
    public void setX(final int coordX) {
        this.coordX = coordX;
    }
    /**
 	   Get de la coordenada Y
     */
    public int getY() {
        return coordY;
    }
    
    /** 
     * Set de la coordenada Y
     */
    public void setY(final int coordY) {
        this.coordY = coordY;
    }

    /**
     * Metodo toString de la clase
     */
	@Override
	public String toString() {
		return "Punto [x=" + coordX +", y="+ coordY +"]";
	}
    
    
}
