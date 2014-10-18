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
		//Mientras haya instancias por asignar.
		while(iteradorInstancias.hasNext())
		{
			//Calculo un número de cluster aleatorio dentro del rango.
			introductorAleatorioCluster=(int)Math.random()*this.getK();
			//Extraigo la siguiente instancia.
			instanciaAleatoria=iteradorInstancias.next();
			//Añado la instancia al cluster elegido.
			this.clusters.anadirInstanciaACluster(introductorAleatorioCluster, instanciaAleatoria);
		}
		//Calculo los centroides para dejar el algoritmo preparado.
		this.calcularCentroides();
		
	}

}
