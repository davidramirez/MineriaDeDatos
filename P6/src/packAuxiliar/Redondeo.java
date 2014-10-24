package packAuxiliar;

/**
 * Clase que contiene funciones auxiliares de redondeo extraidas de
 * http://www.ahristov.com/tutoriales/Trucos%2Bcortos%2Bde%2BJava/Redondear%2Bun%2Bn%FAmero%2Ba%2BN%2Bdecimales.html
 * 
 * Otra opción sería utilizar la clase BigDecimal
 *
 */
public class Redondeo {

	private static Redondeo miRedondeo = new Redondeo();
	private static int decimales = 3;
	
	private Redondeo()
	{
		
	}
	
	public static Redondeo getRedondeo()
	{
		return miRedondeo;
	}
	
	/**
	 * Dado un número real, lo redondea al número de decimales indicado por defecto en el atributo "decimales"
	 * @param numero
	 * El número a redondear
	 * @return
	 * El "numero" redondeado a los decimales indicados
	 */
	public double redondear( double numero) {
		return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	  }
	
	/**
	 * Dado un múmero real, lo redondea a tantos decimales como se indique por parámetro
	 * @param numero
	 * el real a rendondear
	 * @param decimales
	 * el número de decimales que se quieren mantener
	 * @return
	 * el número redondeado
	 */
	public double redondear( double numero, int decimales ) {
	    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	  }
}
