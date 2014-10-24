package packKMeans;

import java.util.HashSet;

import packDistancias.Distancia;
import packInstancias.ListaInstancias;

/**
 * Clase que implementa la inicialización del algoritmo K-means mediante la extracción de k instancias de forma
 *  aleatoria del conjunto de instancias proporcionado para que actúen de centroides
 *
 */
public class InicializacionAleatoria extends KMeans {

	public InicializacionAleatoria(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		super(pK,pDistancia,pListaInstancias,pNumIt,pDelta);
	}
	
	
	/**
	 * Inicializa KMeans usando el método de inicialización aleatoria. Establece los centroides de la primera inicialización copiando K instancias aleatorias del espacio de muestra.
	 */
	public void inicializar()
	{
		int numeroInstancias=this.getInstancias().getNumeroInstancias();
		HashSet<Integer> instanciaExtraidas= new HashSet<Integer>(); 
		int extractorAleatorioInstancias;
		//Para calcular K centroides
		for(int indice=0;indice<this.getK();indice++)
		{
			//Mientras no extraiga una instancia no extraida previamente
			do
			{
				//Extraigo una instancia nueva.
				extractorAleatorioInstancias = (int) (Math.random()*(numeroInstancias));
			}while(instanciaExtraidas.contains(extractorAleatorioInstancias));
			
			//Añado la instancia extraida a las evaluadas.
			instanciaExtraidas.add(extractorAleatorioInstancias);
			//Establezco la instancia como centroide.
			this.centroides[indice]=this.getInstancias().getInstancia(extractorAleatorioInstancias);	
		}
		//Calculo las pertenencias para dejar la inicialización preparada.
		this.calcularPertenencias();
		
	}
	
	@Override
	public String paramToString() {
		return "-i-aleatoria-k-"+this.k+this.distancia.paramToString();
	}


	
}
