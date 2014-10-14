package packAlgoritmia;

import java.util.Vector;

import packDistancias.Distancia;
import packInstancias.ListaInstancias;

public class InicializacionAleatoria extends KMeans {

	public InicializacionAleatoria(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		super(pK,pDistancia,pListaInstancias,pNumIt,pDelta);
	}
	
	
	/**
	 * 
	 */
	public void inicializar()
	{
		int numeroInstancias=this.getInstancias().getNumeroInstancias();
		
		for(int indice=0;i<this.getK();indice++)
		{
			this.getInstancias().getInstancia((int)Math.random()*(numeroInstancias));
		}
			instanciaAleatoria[j] = Math.random()*(maximos[j]-minimos[j])+minimos[j];
		
	}
	


	
}
