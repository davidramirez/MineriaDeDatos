package packInstancias;

import java.util.Vector;

import packAuxiliar.Redondeo;

/**
 * Clase que representa a una instancia. Limitada a atributos únicamente numéricos
 *
 */
public class Instancia {

	private Vector<Double> vector;
	
	/**
	 * Crea una instancia a partir de un vector de características
	 * @param pVector
	 */
	public Instancia(Vector<Double> pVector)
	{
		this.setVector(pVector);
	}
	/**
	 * 
	 * @param pInstancia: Una instancia cualquiera
	 * @return Retorna true si los vectores tienen misma dimensión, false en caso contrario
	 */
	public boolean mismaDimension(Instancia pInstancia) {
		return this.dimension() == pInstancia.dimension();
	}
	
	/**
	 * Función que retorna la dimensión de la instancia.
	 * @return
	 */
	public int dimension()
	{
		return this.getVector().size();
	}

	/*
	 * GETTERS & SETTERS
	 */
	public Vector<Double> getVector() {
		return vector;
	}

	public void setVector(Vector<Double> vector) {
		this.vector = vector;
	}
	
	/**
	 * Imprime el vector de la instancia con sus atributos redondeados a 3 decimales
	 */
	public String toString()
	{
		String atributos = "(";
		
		for(int i = 0; i < this.vector.size(); i++)
		{
			atributos = atributos+ Redondeo.getRedondeo().redondear(this.vector.get(i));
			if(i != this.vector.size()-1)
			{
				atributos = atributos + " ; ";
			}
		}
		atributos = atributos + ")";
		return atributos;
	}
	
	/**
	 * Devuelve la instancia como un string
	 * @return
	 * La representación de la instancia como un string en formato CSV
	 */
	public String toCSV() {
		String atributos = new String();
		
		for(int i = 0; i < this.vector.size(); i++)
		{
			atributos = atributos+this.vector.get(i);
			if(i != this.vector.size()-1)
			{
				atributos = atributos + ",";
			}
		}
		return atributos;
	}
}
