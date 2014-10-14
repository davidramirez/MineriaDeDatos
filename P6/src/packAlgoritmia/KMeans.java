package packAlgoritmia;

import java.util.Iterator;

import packCluster.ListaCluster;
import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

/**
 * 
 * @author david
 *
 */
public abstract class KMeans {
	
	protected int k;//el número predefinido de clusters que se van a realizar
	protected Distancia distancia;
	protected ListaInstancias instancias;
	protected int numIteraciones;
	protected double delta;
	
	protected Instancia[] centroides;
	protected boolean[][] matrizPertenencia;
	
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
		
		//while convergencia > delta
		
			this.calcularPertenencias();
			
			this.calcularCentroides();
			
		
		return lista;	
			
	}
	
	protected abstract void inicializar();
	
	/**
	 * Calcula a cual de los centroides está más próxima cada instancia. Se basa en el valor del atributo centroides
	 *  y en la métrica establecida.
	 *  
	 *  Considera el caso de que una instancia pueda asociarse a varios codewords
	 */
	private void calcularPertenencias()
	{
		//inicializar la matriz de pertenencias, por defecto al crear la matriz, sus posiciones valen FALSE
		this.matrizPertenencia = new boolean[this.instancias.getNumeroInstancias()][k];
		
		//Recorremos la lista de instancias
		Iterator<Instancia> it = this.instancias.getIterador();
		Instancia instanciaActual;
		
		while(it.hasNext())
		{
			instanciaActual = it.next();
			
			//calcuar la distancia respecto a cada codeword
			for(int i = 0; i < centroides.length; i++)
			{
				try {
					this.distancia.distancia(instanciaActual, centroides[i]);
				} catch (Exception e) {
					System.err.println("Error al calcular la distancia entre dos instancias: \n" + e.getMessage() + "\n El programa finalizará");
					System.exit(1);
				}
			}
		}
		
	}
	
	protected void calcularCentroides()
	{
		
	}
	
	public Instancia[] getCentroides() {
		return centroides;
	}
	
	
	public void setCentroides(Instancia[] centroides) {
		this.centroides = centroides;
	}
	
	
	public int getK() {
		return k;
	}
	
	
	public void setK(int k) {
		this.k = k;
	}
	
	
	public Distancia getDistancia() {
		return distancia;
	}
	
	
	public void setDistancia(Distancia distancia) {
		this.distancia = distancia;
	}
	
	
	public ListaInstancias getInstancias() {
		return instancias;
	}
	
	
	public void setInstancias(ListaInstancias instancias) {
		this.instancias = instancias;
	}
	
	
	public int getNumIteraciones() {
		return numIteraciones;
	}
	
	
	public void setNumIteraciones(int numIteraciones) {
		this.numIteraciones = numIteraciones;
	}
	
	
	public double getDelta() {
		return delta;
	}
	
	
	public void setDelta(double delta) {
		this.delta = delta;
	}

}
