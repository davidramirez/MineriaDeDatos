package packAlgoritmia;

import java.util.Vector;

import packDistancias.Distancia;
import packFicheros.CargadorFichero;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

public class DivisionEspacio extends KMeans {

	public DivisionEspacio(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta) {
		super(pK, pDistancia, pListaInstancias, pNumIt, pDelta);
	}

	/**
	 * Inicialización de KMeans que obtiene los parámetros usando una interpretación propia del algoritmo divisor del espacio que divide cada dimensión espacial es K partes y asigna el centro de cada parte a un atributo del atributo del vector de centroides.
	 * 
	 */
	protected void inicializar() {
		double maximos[];
		double minimos[];
		
		maximos=this.getInstancias().getMaximos();
		minimos=this.getInstancias().getMinimos();
		
		int dimensionEspacial=this.getInstancias().getDimension();
		Vector<Double> instanciaTemporal=new Vector<Double>();
		for(int indice=0;indice<dimensionEspacial;indice++)
		{
			for(int indiceAtributos=0;indiceAtributos<dimensionEspacial;indiceAtributos++)
			{
				instanciaTemporal.add(((maximos[indiceAtributos]-minimos[indiceAtributos])/(this.k))-this.k/2);
			}
			this.centroides[indice]=new Instancia(instanciaTemporal);
		}
		
	}

}
