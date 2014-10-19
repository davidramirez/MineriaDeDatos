package packInstancias;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Contiene y gestiona un conjunto de instancias
 *
 */
public class ListaInstancias {

	private LinkedList<Instancia> listaInstancia;
	private String[] nombreAtributos;
	
	private int dimension;//guarda la dimensión de las instancias que contiene este objeto
	
	public ListaInstancias()
	{
		listaInstancia = new LinkedList<Instancia>();
		nombreAtributos = null;
	}
	
	/**
	 * Calcula los valores máximos de cada dimensión para las instancias contenidas.
	 * @return
	 * Retorna un vector de double que contiene estos máximos para cada dimensión.
	 */
	public double[] getMaximos() {
		double maximos[]=new double[getDimension()];
		
		//array a 0 al crearlo
		
		Iterator<Instancia> iterador=this.getListaInstancia().iterator();
		int dimension=getDimension();
		 	
		Instancia instanciaTemporal;
		double maximoTemporal;
		
		while(iterador.hasNext())
		{
			maximoTemporal=0;
			instanciaTemporal=iterador.next();
			for(int i=0;i<dimension;i++)
			{
				
				maximoTemporal=instanciaTemporal.getVector().elementAt(i);
				if(maximoTemporal>maximos[i])
				{
					maximos[i]=maximoTemporal;
				}
			}
		}
		return maximos;
	}	
	
	/**
	 * Calcula los valores mínimos de cada dimensión para las instancias contenidas.
	 * @return
	 * Retorna un vector de double que contiene estos mínimos para cada dimensión.
	 */
	public double[] getMinimos() {
		double minimos[]=new double[getDimension()];
		
		for(int i =0; i<getDimension();i++)
			minimos[i]=Double.MAX_VALUE;
		
		Iterator<Instancia> iterador=this.getListaInstancia().iterator();
		int dimension=getDimension();
		 	
		Instancia instanciaTemporal;
		double maximoTemporal;
		
		while(iterador.hasNext())
		{
			maximoTemporal=0;
			instanciaTemporal=iterador.next();
			for(int i=0;i<dimension;i++)
			{
				
				maximoTemporal=instanciaTemporal.getVector().elementAt(i);
				if(maximoTemporal<minimos[i])
				{
					minimos[i]=maximoTemporal;
				}
			}
		}
		return minimos;
		
	}
	
	/**
	 * Retorna el elmento pPosicion de la lista.
	 * @param pPosicion
	 * @return
	 */
	public Instancia getInstancia(int pPosicion) 
	{
		return this.listaInstancia.get(pPosicion);	
	}
	/*
	 * GETTERS & SETTERS
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * 
	 * @param pDimension
	 */
	public void setDimension(int pDimension)
	{
		this.dimension = pDimension;
	}
	
	private LinkedList<Instancia> getListaInstancia() {
		return listaInstancia;
	}
	
	public String[] getNombresAtributos()
	{
		return nombreAtributos;
	}
	
	public void setNombresAtributos(String[] pNombres)
	{
		this.nombreAtributos = pNombres;
	}
	
	/**
	 * Dada una instancia, la añade al final de la lista que ya teníamos
	 * @param pInstancia
	 * instancia a añadir
	 */
	public void anadirInstancia(Instancia pInstancia)
	{
		if(pInstancia != null)
		{
			this.listaInstancia.addLast(pInstancia);
		}
	}
	
	/**
	 * Devuelve el número de instancias que contiene la lista
	 * @return
	 * número de instancias de la lista
	 */
	public int getNumeroInstancias()
	{
		return this.listaInstancia.size();
	}

	public Iterator<Instancia> getIterador()
	{
		return this.listaInstancia.iterator();
	}


	/**
	 * Devuelve una instancia cuyos atributos son iguales a la media de los de las instancias contenidas en esta lista
	 * @return
	 * La instancia media de la lista, el centroide
	 */
	public Instancia calcularInstanciaMedia() {
		Iterator<Instancia> it = this.getIterador();
		Instancia instanciaActual;
		
		Vector<Double> media = new Vector<Double>(this.dimension);
		//inicializar el vector a 0
		for(int i = 0; i < this.dimension; i++)
		{
			media.add(0.0);
		}
		//acumulamos los valores de cada uno de los atributos de cada instancia
		while(it.hasNext())
		{
			instanciaActual = it.next();
			//acumulamos los valores de la instancia
			
			for(int i = 0;i < this.dimension; i++)
			{
				media.set(i, media.get(i)+instanciaActual.getVector().get(i));
				
			}
		}
		
		//dividimos entre el total de instancias
		for(int i = 0; i < this.dimension; i++)
		{
			media.set(i, (media.get(i)/this.getNumeroInstancias()));
		}
		
		
		return (new Instancia(media));
	}

	/**
	 * Devuelve los nombres de los atributos de las instancias contenidas en la lista en formato CSV
	 * @return
	 * Un string en formato CSV
	 */
	public String getNombresCSV() {
		String atributos = new String();
		
		for(int i = 0; i < this.nombreAtributos.length; i++)
		{
			atributos = atributos+this.nombreAtributos[i];
			if(i != this.nombreAtributos.length-1)
			{
				atributos = atributos + ",";
			}
		}
		return atributos;
	}

	/**
	 * Comprueba la existencia de una instancia concreta en la lista
	 * @param pInstancia
	 * La instancia a comprobar
	 * @return
	 * true - Si la instancia pertenece a la lista
	 * false - Si la instancia no pertenece a la lista
	 */
	public boolean pertenece(Instancia pInstancia) {
		return this.listaInstancia.contains(pInstancia);
	}



}
