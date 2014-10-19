package packKMeans;

import java.util.Iterator;

import packCluster.ListaCluster;
import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

/**
 * Clase que implementa la inicialización del algoritmo K-means asignando cada instancia del conjunto
 *  de trabajo a un cluster. Posteriormente se calculan los centroides resultantes
 *
 */
public class PertenenciaAleatoria extends KMeans {

	public PertenenciaAleatoria(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta) {
		super(pK, pDistancia, pListaInstancias, pNumIt, pDelta);
	}

	/**
	 * Inicializa KMeans utilizando el método de pertenencias aleatorias, asigna cada instancia del conjunto de estudio a uno de los K clusters establecidos por el usuario.
	 */
	protected void inicializar() {
	
		this.clusters=new ListaCluster(centroides, instancias.getDimension());
		
		Iterator<Instancia> iteradorInstancias=this.instancias.getIterador();
		Instancia instanciaAleatoria;
		int introductorAleatorioCluster;
		//Mientras haya instancias por asignar.
		while(iteradorInstancias.hasNext())
		{
			//Calculo un número de cluster aleatorio dentro del rango.
			introductorAleatorioCluster=(int)(Math.random()*this.getK());
			//Extraigo la siguiente instancia.
			instanciaAleatoria=iteradorInstancias.next();
			//Añado la instancia al cluster elegido.
			this.clusters.anadirInstanciaACluster(introductorAleatorioCluster, instanciaAleatoria);
		}
		//Calculo los centroides para dejar el algoritmo preparado.
		this.calcularCentroides();
		
		//Para que cuadre con el algoritmo principal (ejecutar de kmeans)
		this.centroides = this.centroidesNuevos;
		
	}

}
