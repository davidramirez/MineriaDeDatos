package packAlgoritmia;

public abstract class KMeans {
	
	private int k;
	private Distancia distancia;
	private ListaInstancias instancias;
	private int numIteraciones;
	private double delta;
	
	public KMeans(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		k = pK;
		distancia = pDistancia;
		instancias = pListaInstancias;
		numIteraciones = pNumIt;
		delta = pDelta;
	}
	
	public ListaCluster ejecutar()
	{
		ListaCluster lista = null;
				
		this.inicializar();
		
		//while
		
			this.calcularPertenencias();
			
			this.calcularCentroides();
			
		
		return lista;	
			
	}
	
	protected abstract void inicializar();
	
	public void calcularPertenencias()
	{
		
	}
	
	public void calcularCentroides()
	{
		
	}

}
