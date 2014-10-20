package packCluster;

import java.io.PrintStream;

import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

/**
 * Esta clase representa un cluster. El cluster se caracteriza por un centroide (que es una instancia) 
 * y una lista de instancias que han sido asociadas a este cluster.
 * 
 * Además se tiene en cuanta el errorAcumulado que es la suma de las distancias entre cada instancia 
 * perteneciente al cluster y el centroide utilizando la distancia que hubiera sido definida en el algoritmo
 *
 */
public class Cluster {
	 private Instancia centroide;
	 private ListaInstancias listaInstancias;
	 private Double errorAcumulado;
	 private Double errorCuadráticoAcumulado;
	 
	 /**
	  * A partir de un centroide y la dimensión de las instancias a guardar crea el cluster. Crea un nuevo objeto 
	  * ListaInstancias para guardar las que pertenezcan al mismo y establece a 0 el error acumulado
	  * @param pCentroide
	  * una Instancia que será el centroide del cluster. Puede ser null.
	  * @param pDimension
	  * El número de atributos que tendrán las instancias de la lista
	  */
	 public Cluster(Instancia pCentroide, int pDimension)
	 {
		 this.listaInstancias = new ListaInstancias();
		 this.listaInstancias.setDimension(pDimension);
		 this.centroide = pCentroide;
		 this.errorAcumulado = 0.0;
		 this.errorCuadráticoAcumulado = 0.0;
	 }
	
	
	/*
	 * GETTERS & SETTERS
	 */
	
	public Instancia getCentroide() {
		return centroide;
	}
	
	/**
	 * Añade una nueva instancia a la lista de instancias del cluster
	 * @param pInstancia
	 * instancia a añadir
	 */
	public void anadirInstancia(Instancia pInstancia)
	{
		this.listaInstancias.anadirInstancia(pInstancia);
	}

	/**
	 * A partir de las instancias contenidas en el cluster, calcula el nuevo centroide como la 
	 * instancia cuyos atributos son iguales a la media del mismo atributo en cada instancia de la lista
	 * @return
	 * El nuevo centroide de la lista
	 */
	public Instancia calcularNuevoCentroide() {
		return this.listaInstancias.calcularInstanciaMedia();
	}

	/**
	 * Dado un nuevo centroide y una función de distancia calcula su divergencia como la distancia entre el nuevo centroide 
	 * y el propio del cluster.
	 * @param pCentroide
	 * El centroide respecto del cual se calculará la distancia
	 * @param pDistancia
	 * La función de distancia a utilizar en el cálculo
	 * @return
	 * La distancia entre el centroide del cluster y el del parámetro
	 */
	public double calcularDivergencia(Instancia pCentroide, Distancia pDistancia) {
		try {
			return (pDistancia.distancia(pCentroide, this.centroide));
		} catch (Exception e) {
			System.err.println("Error al calcular la distancia entre dos instancias: \n" + e.getMessage() + "\n El programa finalizará");
			System.exit(1);
		}
		
		//si algo falla, la divergencia devuelta ess infinita
		return Double.MAX_VALUE;
	}

	/**
	 * Dado un valor correspondiente con la distancia entre una instancia y un centroide, lo acumula, acumulando también su cuadrado
	 * @param pError
	 * Un valor real. Debería corresponder con la distancia entre una instancia y un centroide ambos pertenecieentes a este cluster
	 */
	public void acumularError(double pError) {
		this.errorAcumulado = this.errorAcumulado + pError;
		this.errorCuadráticoAcumulado += Math.pow(pError, 2);
	}


	/**
	 * Imprime el estado actual del cluster en el descriptor de fichero dado
	 * @param pNumCluster
	 * El número que le corresponde a este cluster
	 * @param ps
	 * El descriptor de ficehro donde se escribirá la salida
	 */
	public void imprimirEstado(int pNumCluster, PrintStream ps) {
		
		ps.println(pNumCluster+"\t"+this.centroide.toString()+"\t"+ this.listaInstancias.getNumeroInstancias()+"\t"+this.getErrorAcumuladoMedio());
		
	}


	/**
	 * Calcula el error medio de asignación cometido en el cluster
	 * @return
	 * el error acumulado entre el número de instancias asignadas al cluster
	 */
	public double getErrorAcumuladoMedio() {
		if(this.listaInstancias.getNumeroInstancias() == 0) return 0;
		return (this.errorAcumulado/this.listaInstancias.getNumeroInstancias());
	}


	public int getNumInstancias() {
		return this.listaInstancias.getNumeroInstancias();
	}


	/**
	 * Comprueba si la instancia pertenece al cluster
	 * @param pInstancia
	 * instancia a comprobar
	 * @return
	 * true - si la instancia pertenece al cluster
	 * false - si no pertenece
	 */
	public boolean pertenece(Instancia pInstancia) {
		return this.listaInstancias.pertenece(pInstancia);
	}
}
