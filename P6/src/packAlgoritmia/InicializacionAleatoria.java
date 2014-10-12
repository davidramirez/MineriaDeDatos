package packAlgoritmia;

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
		
		ListaInstancias centroides=new ListaInstancias();
		for(int i=0;i<pNumeroClusters;i++)
		{
			for(int j=0;j<dimension;j++)
			{
				instanciaAleatoria[j] = Math.random()*(maximos[j]-minimos[j])+minimos[j];
			}
			centroides.getListaInstancia().add(new Instancia(instanciaAleatoria));
		}
	}
	
}
