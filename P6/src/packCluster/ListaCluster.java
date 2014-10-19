package packCluster;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import packDistancias.Distancia;
import packInstancias.Instancia;

/**
 * Representa el clustering realizado sobre un conjunto de instancias.
 * 
 * Contiene los diferentes clusters identificados
 *
 */
public class ListaCluster {

	private ArrayList<Cluster> listaCluster;
	
	/**
	 * Crea la lista de clusters a partir de los diferentes centroides de cada cluster y la dimensión de las instancias tratadas
	 * @param centroides
	 * Los centroides de los clusters. Los diferentes centroides de esta lista pueden ser nulos.
	 * @param pDimension
	 * El número de atributos que contienen las instancias tratadas
	 */
	public ListaCluster(Instancia[] centroides, int pDimension)
	{
		listaCluster = new ArrayList<Cluster>(centroides.length);
		
		for(int i = 0; i < centroides.length; i++)
		{
			listaCluster.add(i, new Cluster(centroides[i], pDimension));
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
	
	/**
	 * Devuelve una lista con los centroides característicos de cada cluster
	 * @return
	 */
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
	
	/**
	 * Dada una instancia, la añade al cluster correspondiente
	 * @param pCluster
	 * El índice que identifica al cluster al que añadir la instancia
	 * @param pInstancia
	 * La instancia a añadir
	 */
	public void anadirInstanciaACluster(int pCluster, Instancia pInstancia)
	{
		if(pCluster < this.getListaCluster().size())
		{
			this.getListaCluster().get(pCluster).anadirInstancia(pInstancia);
		}
	}

	/**
	 * Calcula una lista que contendrá los centroides nuevos de cada cluster
	 * @return
	 * Una lisa conteniendo los centroides nuevos de cada cluster
	 */
	public Instancia[] calcularNuevosCentroides() {
		Instancia[] nuevosCentroides = new Instancia[this.getListaCluster().size()];
		
		for(int i = 0; i < nuevosCentroides.length; i++)
		{
			nuevosCentroides[i] = this.getListaCluster().get(i).calcularNuevoCentroide();
		}
		
		return nuevosCentroides;
	}

	/**
	 * Calcula la diferencia entre los centroides contenidos en los diferentes clusters y los obtenidos 
	 * por parámetro.
	 * @param pCentroides
	 * Los centroides respecto de los que calcular la divergencia
	 * @param pDistancia
	 * La función de distancia a utilizar para calcular la diferencia entre los diferentes centroides
	 * @return
	 * La suma de las diferencias entre centroides correspondientes utilizando la función de distancia dada
	 */
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
	
	/**
	 * Imprime el estado del clustering representado por esta instancia en el descriptor de fichero indicado
	 * @param ps
	 * El descriptor de fichero en el que imprimir el estado
	 */
	public void imprimirEstado(PrintStream ps)
	{
		ps.println("Cluster \t Centroide \t NumInstancias \t Error");
		
		Iterator<Cluster> it = this.getIterator();
		Cluster clusterActual;
		int i = 1;
		
		while(it.hasNext())
		{
			clusterActual = it.next();
			
			clusterActual.imprimirEstado(i, ps);
			
			i++;
		}
		
		ps.println();
		ps.println("Error total: "+this.calcularErrorTotal() + "\n \n");
	}

	/**
	 * Devuelve el error total cometido durante el clustering
	 * @return
	 * El error como la suma de los errores parciales cometidos en cada cluster
	 */
	public Double calcularErrorTotal() {
		
		Iterator<Cluster> it = this.getIterator();
		Cluster clusterActual;
		double error = 0.0;
		int instancias = 0;
		
		while(it.hasNext())
		{
			clusterActual = it.next();
			
			instancias = instancias + clusterActual.getNumInstancias();
			error = error + clusterActual.getErrorAcumuladoMedio();
		}
		return (error/instancias);
	}

	/**
	 * Acumula el error indicado en el cluster correspondiente
	 * @param pCluster
	 * El índice del cluster al que acume¡ular el error
	 * @param pError
	 * El error a acumular. Valor real.
	 */
	public void acumularErrorEnCluster(int pCluster, double pError) {
		this.getListaCluster().get(pCluster).acumularError(pError);
	}
}
