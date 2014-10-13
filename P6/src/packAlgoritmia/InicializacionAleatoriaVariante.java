package packAlgoritmia;

import java.util.Vector;

import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

public class InicializacionAleatoriaVariante extends KMeans{
	
	public InicializacionAleatoriaVariante(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		super(pK,pDistancia,pListaInstancias,pNumIt,pDelta);
	}
	
	
	/**
	 * 
	 */
	public void inicializar()
	{
		double maximos[];
		double minimos[];
		
		maximos=this.getInstancias().getMaximos();
		minimos=this.getInstancias().getMinimos();
		
		int dimension=this.getInstancias().getDimension();
		double instanciaAleatoria[] = new double[this.getInstancias().getDimension()];
		Vector<Double> vectorTemporal = new Vector<Double>();
		this.centroides = new Instancia[this.k];
		for(int i=0;i<this.k;i++)
		{
			for(int j=0;j<dimension;j++)
			{
				instanciaAleatoria[j] = Math.random()*(maximos[j]-minimos[j])+minimos[j];
			}
			for(int k=0;k<dimension;k++)
			{
				vectorTemporal.add(instanciaAleatoria[k]);
			}
			centroides[i] = new Instancia(vectorTemporal);
		}
	}

}
