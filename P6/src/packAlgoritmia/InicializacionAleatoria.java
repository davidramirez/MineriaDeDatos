package packAlgoritmia;

import java.util.HashSet;
import java.util.Vector;

import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

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
		
		for(int indice=0;indice<this.getK();indice++)
		{
			do
			{
				extractorAleatorioInstancias=(int)Math.random()*(numeroInstancias);
			}while(instanciaExtraidas.contains(extractorAleatorioInstancias));
			
			instanciaExtraidas.add(extractorAleatorioInstancias);
			this.centroides[indice]=this.getInstancias().getInstancia(extractorAleatorioInstancias);	
		}
		
		this.calcularPertenencias();
		
	}
	


	
}
