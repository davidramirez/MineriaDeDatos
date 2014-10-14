package packCluster;

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
	
	private Instancia calcularCentroide() {
		// TODO Auto-generated method stub
		return null;
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
}
