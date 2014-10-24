package packKMeans;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;

import packAuxiliar.Redondeo;
import packCluster.ListaCluster;
import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;
import packTiempo.StopWatch;

/**
 * Clase abstracta que define la implementación del algoritmo K-means clustering
 * 
 * La implementación de los diferentes tipos de inicialización del algoritmo se realiza en las diferentes cases hijas
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
	
	/**
	 * Prepara una instancia para la ejecución del algoritmo k-means
	 * @param pK
	 * Número de clusters a formar
	 * @param pDistancia
	 * Función de distancia a utilizar en los cálcullos
	 * @param pListaInstancias
	 * Instancias sobre las que realizar el clustering
	 * @param pNumIt
	 * Número de iteraciones que el algoritmo realizará
	 * @param pDelta
	 * Margen de convergencia permitido entre los diferentes grupos de centroides de dos iteraciones contiguas. Cuando el margen quede satisfecho, finalizará el algoritmo
	 */
	public KMeans(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		this.k = pK;
		this.distancia = pDistancia;
		this.instancias = pListaInstancias;
		this.numIteraciones = pNumIt;
		this.delta = pDelta;
		
		this.centroides=new Instancia[pK];
		this.centroidesNuevos=new Instancia[pK];
		this.clusters=new ListaCluster(centroides, instancias.getDimension());
	}
	
	
	/**
	 * Algotimo k-means clustering
	 * @param salidaInf 
	 * @return
	 * Los k clusters obtenidos en el proceso
	 */
	public void ejecutar(PrintStream salidaInf)
	{
		
		
		//contamos el tiempo total de ejecución
		StopWatch tiempo = new StopWatch();
		
		//inicializamos el problema dependiendo de la opción elegida		
		System.out.println("Ejecutando inicialización...");
		this.inicializar();
		
		//inicialmente queremos que se ejecute el algoritmo, establecemos la convergencia a un valor enorme
		double convergencia = Double.MAX_VALUE;
		int i = 1;//numero de iteración que relizamos
		
		
		this.centroidesNuevos = this.centroides;
				
		while(convergencia > delta && i <= this.numIteraciones)
		{
			System.out.println("Iterando... iteración: " + i);
			salidaInf.println("Iterando... iteración: " + i);
		
			//Actualizamos los centroides actuales
			this.centroides = this.centroidesNuevos;
		
			this.calcularPertenencias();//crea nuevos clusters a partir  del atrib centroides
			
			this.calcularCentroides();//Calcula los nuevos, los dejara en centroidesnuevos
			
			//imprimimos el estado tras esta vuelta
			this.clusters.imprimirEstado(System.out);
			this.clusters.imprimirEstado(salidaInf);
			
			convergencia = this.calcularDivergenciaCentroides();
			
			System.out.println("Convergencia entre los centroides anteriores y nuevos: "+Redondeo.getRedondeo().redondear(convergencia, 5)+"\n\n");
			salidaInf.println("Convergencia entre los centroides anteriores y nuevos: "+Redondeo.getRedondeo().redondear(convergencia, 5)+"\n\n");
			
			i++;
		}
		System.out.println("\nInstancias totales en la realización del clustering: "+this.instancias.getNumeroInstancias());
		salidaInf.println("\nInstancias totales en la realización del clustering: "+this.instancias.getNumeroInstancias());
		System.out.println("Tiempo total en la ejecución de kmeans: "+tiempo.elapsedTime() + "  segundos");
		salidaInf.println("Tiempo total en la ejecución de kmeans: "+tiempo.elapsedTime() + "  segundos");
			
			
	}
	
	/**
	 * Calcula la suma de la diferencia entre las distancias de los centroides de los clusters identificados respecto a sus correspondientes 
	 * de una lista de nuevos centroides
	 * @return
	 * La divergencia entre el nuevo conjunto de centroides y el antiguo
	 */
	private double calcularDivergenciaCentroides() {
		
		return this.clusters.calcularDivergencia(this.centroidesNuevos, this.distancia);
	}

	/**
	 * Método abstracto que define la inicialización del algoritmo
	 */
	protected abstract void inicializar();
	
	/**
	 * Calcula a cuál de los centroides está más próxima cada instancia. Se basa en el valor del atributo centroides
	 *  y en la métrica establecida.
	 *  
	 *  Considera el caso de que una instancia pueda asociarse a varios codewords
	 */
	protected void calcularPertenencias()
	{
		//inicializar los clusters para esta vuelta
		this.clusters = new ListaCluster(centroides, instancias.getDimension());
		
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
			
			//pasamos los resultados a La lista de clusters, asignando la instancia actual a su respectivo cluser y acumulando el error
			Iterator<Integer> iteradorCentroide = centroidesProximos.iterator();
			int i;
			
			while(iteradorCentroide.hasNext())
			{
				i = iteradorCentroide.next();
				this.clusters.anadirInstanciaACluster(i, instanciaActual);
				this.clusters.acumularErrorEnCluster(i, distMin);
			}
		}
		
	}
	
	/**
	 * Calcula los nuevos centroides utilizando las instancias que han sido agrupadas en los diferentes clusters
	 */
	protected void calcularCentroides()
	{
		this.centroidesNuevos = this.clusters.calcularNuevosCentroides();
	}
	
	/**
	 * Guarda las instancias en el orden de carga añadiendo un atributo "clase" corresponidiente 
	 * al cluster asignado tras la ejecución del algoritmo. El formato es CSV
	 * 
	 * Se debe llamar tras la ejecución del algoritmo, cuando la estrructura ListaCluster contiene el resultado
	 * @param ps
	 * El fichero en que guardar las instancias
	 * @param string 
	 */
	public void guardarInstanciasClasificadas(PrintStream ps, String param)
	{
		ps.println("% Instancias clasificadas con el algoritmo K-means");
		ps.println("% Parámetros de la llamada: "+ param +"\n\n");
		
		ps.println(this.instancias.getNombresCSV()+",clase\n\n");
		
		Iterator<Instancia> it = this.instancias.getIterador();
		Instancia instanciaActual;
		
		while(it.hasNext())
		{
			instanciaActual = it.next();
			
			ps.println(instanciaActual.toCSV()+","+this.clusters.getNumClusterAsignado(instanciaActual));
		}
	}
	
	
	public int getK() {
		return k;
	}
	
	public ListaInstancias getInstancias() {
		return instancias;
	}
	
	/**
	 * Devuelve un string que indica la configuración de parámetros del algoritmo
	 * @return
	 */
	public abstract String paramToString();
	
	/**
	 * Devuelve el Error Cuadrático Medio resultante tras la ejecución del algoritmo
	 * @return
	 * ECM
	 */
	public double getECM()
	{
		return clusters.calcularErrorCuadraticoMedio();
	}

}
