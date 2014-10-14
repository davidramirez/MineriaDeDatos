package packDistancias;

import packInstancias.Instancia;

public abstract class Distancia {

	/**
	 * Calcula la distancia entre dos instancias
	 * @param pInstancia1
	 * @param pInstancia2
	 * @return
	 * El valor de la distancia
	 * @throws Exception
	 * Si las instancias son de dimensiones distintas
	 */
	public abstract double distancia(Instancia pInstancia1,Instancia pInstancia2) throws Exception;
	
}
