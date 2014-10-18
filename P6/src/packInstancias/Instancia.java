package packInstancias;

import java.util.Vector;

public class Instancia {

	private Vector<Double> vector;
	
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
	
	public String toString()
	{
		return this.vector.toString();
		
	}
}
