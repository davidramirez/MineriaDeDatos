package packAlgoritmia;

import java.util.Iterator;
import java.util.LinkedList;

public class ListaInstancias {

	private LinkedList<Instancia> listaInstancia=new LinkedList<Instancia>();
	
	
	
	

	public double[] getMaximos() {
		double maximos[]=new double[dimension()];
		
		for(int i =0; i<dimension();i++)
			maximos[i]=0;
		
		Iterator<Instancia> iterador=this.getListaInstancia().iterator();
		int dimension=dimension();
		 	
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
double minimos[]=new double[dimension()];
		
		for(int i =0; i<dimension();i++)
			minimos[i]=Double.MAX_VALUE;
		
		Iterator<Instancia> iterador=this.getListaInstancia().iterator();
		int dimension=dimension();
		 	
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
	
	public int dimension() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * GETTERS & SETTERS
	 */
	public LinkedList<Instancia> getListaInstancia() {
		return listaInstancia;
	}

}
