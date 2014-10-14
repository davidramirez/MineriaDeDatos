package packCluster;

import java.util.ArrayList;
import java.util.Iterator;

import packDistancias.Distancia;
import packInstancias.Instancia;

public class ListaCluster {

	private ArrayList<Cluster> listaCluster;
	
	public ListaCluster(Instancia[] centroides)
	{
		listaCluster = new ArrayList<Cluster>(centroides.length);
		
		for(int i = 0; i < centroides.length; i++)
		{
			listaCluster.set(i, new Cluster(centroides[i]));
		}
	}

	/*
	 * GETTERS & SETTERS
	 */
	
	private ArrayList<Cluster> getListaCluster() {
		return listaCluster;
	}
	
	public Iterator<Cluster> getIterator()
	{
		return this.getListaCluster().iterator();
	}
	
	public Instancia[] getCentroides()
	{
		Iterator<Cluster> it = this.getIterator();
		Cluster c;
		Instancia[] centroides = new Instancia[this.getListaCluster().size()];
		int i = 0;
		
		while(it.hasNext())
		{
			c = it.next();
			
			centroides[i] = c.getCentroide();
			i++;
		}
		
		return centroides;
	}
	
	public void anadirInstanciaACluster(int pCluster, Instancia pInstancia)
	{
		if(pCluster < this.getListaCluster().size())
		{
			this.getListaCluster().get(pCluster).anadirInstancia(pInstancia);
		}
	}

	public Instancia[] calcularNuevosCentroides() {
		Instancia[] nuevosCentroides = new Instancia[this.getListaCluster().size()];
		
		for(int i = 0; i < nuevosCentroides.length; i++)
		{
			nuevosCentroides[i] = this.getListaCluster().get(i).calcularNuevoCentroide();
		}
		
		return nuevosCentroides;
	}

	public double calcularDivergencia(Instancia[] pCentroides, Distancia pDistancia) {
		double divergencia = 0;
		Iterator<Cluster> it = this.getIterator();
		Cluster clusterActual;
		int i = 0;
		
		while(it.hasNext())
		{
			clusterActual = it.next();
			
			divergencia = divergencia + clusterActual.calcularDivergencia(pCentroides[i], pDistancia);
			
			i++;
		}
		return divergencia;
	}
}
