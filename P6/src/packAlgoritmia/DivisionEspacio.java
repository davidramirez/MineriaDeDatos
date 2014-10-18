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
		//obtengo los valores máximos y mínimos de cada espacio
		maximos=this.getInstancias().getMaximos();
		minimos=this.getInstancias().getMinimos();
		
		int dimensionEspacial=this.getInstancias().getDimension();
		Vector<Double> instanciaTemporal;
		
		//Mientras no haya creado k divisiones
		for(int indice=0;indice<this.getK();indice++)
		{
			instanciaTemporal=new Vector<Double>();
			
			//Mientras no haya establecido todos los atributos
			for(int indiceAtributos=0;indiceAtributos<dimensionEspacial;indiceAtributos++)
			{
				//Divido la dimensión en K subespacios, extraigo los centros de esos subespacios y selecciono el correspondiente al subespacio que estamos creando.
				instanciaTemporal.add(((((maximos[indiceAtributos]-minimos[indiceAtributos]))/(this.k))-this.k/2)*indice);
			}
			//Añado el centroide creado fruto de dividir el espacio.
			this.centroides[indice]=new Instancia(instanciaTemporal);
		}
		//Al acabar calculo las pertenencias para dejar el algoritmo preparado.
		this.calcularPertenencias();
		
	}

}
