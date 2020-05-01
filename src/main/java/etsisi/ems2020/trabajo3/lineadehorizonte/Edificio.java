package etsisi.ems2020.trabajo3.lineadehorizonte;


/**
 * Clase Edificio, con los atributos y
 * métodos de un edificio
 */
public class Edificio {
	
	/**
	 * Coordenada izquierda de un edificio
	 */
	private transient int cordIzq;
	
	/**
	 * Altura del edificio
	 */
    private transient int altura;
    
    /**
     * Coordenada derecha de un edificio
     */
    private transient int cordDcha;
    
    
    /**
     * Contructor de la clase
     */
    public Edificio()
    {
        this.cordDcha = 0;
        this.cordIzq = 0;
        this.altura = 0;
    }    
    
    /**
     * Constructor con parametros
     * @param cordIzq
     * @param altura
     * @param cordDcha
     */
    public Edificio(final int cordIzq, final int altura, final int cordDcha)
    {
        this.cordDcha = cordDcha;
        this.cordIzq = cordIzq;
        this.altura = altura;
    }    
    
    /**
     * Devuelve la coordenada X izquierda
     * @return
     */
    public int getXi() {
        return this.cordIzq;
    }
    
    /**
     * Establece un valor en la coordenada X izquierda
     * @param cordIzq
     */
    public void setXi(final int cordIzq) {
        this.cordIzq = cordIzq;
    }
    
    /**
     * Devuelve la altura
     * @return
     */
    public int getY() {
        return this.altura;
    }
    
    /**
     * Establece un valor en la altura
     * @param altura
     */
    public void setY(final int altura) {
        this.altura = altura;
    }
    
    /**
     * Devuelve la coordenada X derecha
     * @return
     */
    public int getXd() {
        return this.cordDcha;
    }
    
    /**
     * Establece un valor en la coordenada Y izquierda
     * @param cordDcha
     */
    public void setXd(final int cordDcha) {
        this.cordDcha = cordDcha;
    }
}
