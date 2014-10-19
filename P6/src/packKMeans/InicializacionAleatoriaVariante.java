package packKMeans;

import java.util.Vector;

import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

/**
 * Clase que implementa la incialización de K-means generando k instancias con valores aleatorios para sus atributos. 
 * Estos valores están comprendidos entre el mínimo y máximo para cada componente de todas las instancias del conjunto de trabajo
 *
 */
public class InicializacionAleatoriaVariante extends KMeans{
	
	public InicializacionAleatoriaVariante(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		super(pK,pDistancia,pListaInstancias,pNumIt,pDelta);
	}
	
	
	/**
	 * Inicializa KMeans usando la inicialización aleatoria variada, esta inicialización examina los rangos entre los que se mueven las variables y asigna centroides aleatorios dentro de esos rangos.
	 */
	public void inicializar()
	{
		double maximos[];
		double minimos[];
		
		//Obtengo los máximos y mínimos de cada dimensión.
		maximos=this.getInstancias().getMaximos();
		minimos=this.getInstancias().getMinimos();
		
		int dimension=this.getInstancias().getDimension();
		double instanciaAleatoria[];
		Vector<Double> vectorTemporal;
		this.centroides = new Instancia[this.k];
		
		//Para cada partición.
		for(int i=0;i<this.k;i++)
		{
			instanciaAleatoria = new double[this.getInstancias().getDimension()];
			vectorTemporal = new Vector<Double>();
			
			//Evaluo las distintas dimensiones.
			for(int j=0;j<dimension;j++)
			{
				//Calculo una posición aleatoria dentro del rango en que está definido cada subespacio.
				instanciaAleatoria[j] = Math.random()*(maximos[j]-minimos[j])+minimos[j];
			}
			for(int k=0;k<dimension;k++)
			{
				//Añado al vectorTemporal cada uno de los valores calculados.
				vectorTemporal.add(instanciaAleatoria[k]);
			}
			//Añado el centroide creado al grupo de centroides
			centroides[i] = new Instancia(vectorTemporal);
		}
		//Calculo las pertenencias para dejar el algoritmo preparado
		this.calcularPertenencias();
	}

}
