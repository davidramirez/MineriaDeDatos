package packAlgoritmia;

import java.util.Iterator;
import java.util.LinkedList;

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
	protected Instancia[] centroidesNuevos;
	protected ListaCluster clusters;
	
	public KMeans(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		this.k = pK;
		this.distancia = pDistancia;
		this.instancias = pListaInstancias;
		this.numIteraciones = pNumIt;
		this.delta = pDelta;
		
		this.centroides=new Instancia[pK];
		this.centroidesNuevos=new Instancia[pK];
		this.clusters=new ListaCluster(centroides);
	}
	
	public ListaCluster ejecutar()
	{
		//inicializamos el problema dependiendo de la opción elegida		
		this.inicializar();
		
		//inicialmente queremos que se ejecute el algoritmo, establecemos la convergencia a un valor enorme
		double convergencia = Double.MAX_VALUE;
		int i = 1;//numero de iteración que relizamos
		
		this.centroidesNuevos = this.centroides;
				
		while(convergencia > delta && i <= this.numIteraciones)
		{
		
			//Actualizamos los centroides actuales
			this.centroides = this.centroidesNuevos;
		
			this.calcularPertenencias();//crea nuevos clusters a partir  del atrib centroides
			
			this.calcularCentroides();//Calcula los nuevos, los dejara en centroidesnuevos
			
			convergencia = this.calcularDivergenciaCentroides();
			
		}
		
		return clusters;	
			
	}
	
	private double calcularDivergenciaCentroides() {
		
		return this.clusters.calcularDivergencia(this.centroidesNuevos, this.distancia);
	}

	protected abstract void inicializar();
	
	/**
	 * Calcula a cual de los centroides está más próxima cada instancia. Se basa en el valor del atributo centroides
	 *  y en la métrica establecida.
	 *  
	 *  Considera el caso de que una instancia pueda asociarse a varios codewords
	 */
	protected void calcularPertenencias()
	{
		//inicializar los clusters para esta vuelta
		this.clusters = new ListaCluster(centroides);
		
		//Recorremos la lista de instancias
		Iterator<Instancia> it = this.instancias.getIterador();
		Instancia instanciaActual;
		
		while(it.hasNext())
		{
			instanciaActual = it.next();
			
			//calcuar la distancia respecto a cada codeword
			//guardamos en cada momento cuales son los codewords más próximos a la instancia y su distancia
			LinkedList<Integer> centroidesProximos = new LinkedList<Integer>();
			double distMin = Double.MAX_VALUE;
			
			for(int i = 0; i < centroides.length; i++)
			{
				try {
						double distActual = this.distancia.distancia(instanciaActual, centroides[i]);
						
						if(distActual < distMin)
						{
							//reiniciamos la lista de centroides proximos con el actual y actualizamos la distancia mínima
							centroidesProximos = new LinkedList<Integer>();
							centroidesProximos.add(i);
							distMin = distActual;
						}
						else if(distActual == distMin)
						{
							//Tenemos otro centroide a la misma distancia
							centroidesProximos.add(i);
						}
					
				} catch (Exception e) {
					System.err.println("Error al calcular la distancia entre dos instancias: \n" + e.getMessage() + "\n El programa finalizará");
					System.exit(1);
				}
			}
			
			//pasamos los resultados a La lista de clusters, asignando la instancia actual a su respectivo cluser
			Iterator<Integer> iteradorCentroide = centroidesProximos.iterator();
			int i;
			
			while(iteradorCentroide.hasNext())
			{
				i = iteradorCentroide.next();
				this.clusters.anadirInstanciaACluster(i, instanciaActual);
			}
		}
		
	}
	
	protected void calcularCentroides()
	{
		this.centroidesNuevos = this.clusters.calcularNuevosCentroides();
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
