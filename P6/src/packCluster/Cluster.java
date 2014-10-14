package packCluster;

import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

public class Cluster {
	 private Instancia centroide;
	 private ListaInstancias listaInstancias;
	 
	 public Cluster(Instancia pCentroide)
	 {
		 this.listaInstancias = new ListaInstancias();
		 this.centroide = pCentroide;
	 }
	
	private double calcularError() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	
	public Instancia getCentroide() {
		return centroide;
	}
	
	
	public void anadirInstancia(Instancia pInstancia)
	{
		this.listaInstancias.anadirInstancia(pInstancia);
	}

	public Instancia calcularNuevoCentroide() {
		return this.listaInstancias.calcularInstanciaMedia();
	}

	public double calcularDivergencia(Instancia pCentroide, Distancia pDistancia) {
		try {
			return (pDistancia.distancia(pCentroide, this.centroide));
		} catch (Exception e) {
			System.err.println("Error al calcular la distancia entre dos instancias: \n" + e.getMessage() + "\n El programa finalizará");
			System.exit(1);
		}
		
		//si algo falla, la divergencia devuelta ess infinita
		return Double.MAX_VALUE;
	}
}
