package packAlgoritmia;

import java.util.Iterator;
import java.util.LinkedList;

public class ListaInstancias {

	private LinkedList<Instancia> listaInstancia=new LinkedList<Instancia>();
	
	
	
	

	public double[] getMaximos() {
		double maximos[];
		
		int numeroInstancias=this.getListaInstancia().size();
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
				instanciaTemporal.getVector().elementAt(i);
			}
		}
	}	
	
	private int dimension() {
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
