package packInstancias;

import java.util.Iterator;
import java.util.LinkedList;

public class ListaInstancias {

	private LinkedList<Instancia> listaInstancia;
	private String[] nombreAtributos;
	
	private int dimension;//guarda la dimensión de las instancias que contiene este objeto
	
	public ListaInstancias()
	{
		listaInstancia = new LinkedList<Instancia>();
		nombreAtributos = null;
	}
	

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
}
