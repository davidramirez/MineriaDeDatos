package packAlgoritmia;

import java.util.Vector;

public class InicializacionAleatoria extends KMeans {
	Instancia[] centroides;
	
	public InicializacionAleatoria(ListaInstancias pListaInstancias, int pNumeroClusters)
	{
		double maximos[];
		double minimos[];
		
		maximos=pListaInstancias.getMaximos();
		minimos=pListaInstancias.getMinimos();
		
		int dimension=pListaInstancias.getListaInstancia().get(0).dimension();
		double instanciaAleatoria[] = new double[pListaInstancias.dimension()];
		Vector<Double> vectorTemporal = new Vector<Double>();
		ListaInstancias centroides=new ListaInstancias();
		for(int i=0;i<pNumeroClusters;i++)
		{
			for(int j=0;j<dimension;j++)
			{
				instanciaAleatoria[j] = Math.random()*(maximos[j]-minimos[j])+minimos[j];
			}
			for(int k=0;k<dimension;k++)
			{
				vectorTemporal.add(instanciaAleatoria[k]);
			}
			centroides.getListaInstancia().add(new Instancia(vectorTemporal));
		}
	}
	
}
