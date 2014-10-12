package packAlgoritmia;

import java.util.Vector;

public class Instancia {

	private Vector<Double> vector = new Vector<Double>();

	public Instancia(double pValores[])
	{
		for(int i=0;i<pValores.length;i++)
		{
			this.getVector().add(pValores[i]);
		}
	}
	/**
	 * 
	 * @param pInstancia: Una instancia cualquiera
	 * @return Retorna true si los vectores tienen misma dimensión, false en caso contrario
	 */
	public boolean mismaDimension(Instancia pInstancia) {
		return this.getVector().size() == pInstancia.getVector().size();
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
}
