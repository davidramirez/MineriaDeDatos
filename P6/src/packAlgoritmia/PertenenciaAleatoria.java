package packAlgoritmia;

import java.util.Iterator;

import packCluster.ListaCluster;
import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

public class PertenenciaAleatoria extends KMeans {

	public PertenenciaAleatoria(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta) {
		super(pK, pDistancia, pListaInstancias, pNumIt, pDelta);
	}

	/**
	 * Inicializa KMeans utilizando el método de pertenencias aleatorias, asigna cada instancia del conjunto de estudio a uno de los K clusters establecidos por el usuario.
	 */
	protected void inicializar() {
	
		this.clusters=new ListaCluster(centroides);
		
		Iterator<Instancia> iteradorInstancias=this.instancias.getIterador();
		Instancia instanciaAleatoria;
		int introductorAleatorioCluster;
		while(iteradorInstancias.hasNext())
		{
			introductorAleatorioCluster=(int)Math.random()*this.getK();
			instanciaAleatoria=iteradorInstancias.next();
			this.clusters.anadirInstanciaACluster(introductorAleatorioCluster, instanciaAleatoria);
		}
		
	}

}
