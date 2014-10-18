package packAlgoritmia;

import java.util.HashSet;
import java.util.Vector;

import packDistancias.Distancia;
import packInstancias.Instancia;
import packInstancias.ListaInstancias;

public class InicializacionAleatoria extends KMeans {

	public InicializacionAleatoria(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta)
	{
		super(pK,pDistancia,pListaInstancias,pNumIt,pDelta);
	}
	
	
	/**
	 * Inicializa KMeans usando el método de inicialización aleatoria. Establece los centroides de la primera inicialización copiando K instancias aleatorias del espacio de muestra.
	 */
	public void inicializar()
	{
		int numeroInstancias=this.getInstancias().getNumeroInstancias();
		HashSet<Integer> instanciaExtraidas= new HashSet<Integer>(); 
		int extractorAleatorioInstancias;
		//Para calcular K centroides
		for(int indice=0;indice<this.getK();indice++)
		{
			//Mientras no extraiga una instancia no extraida previamente
			do
			{
				//Extraigo una instancia nueva.
				extractorAleatorioInstancias=(int)Math.random()*(numeroInstancias);
			}while(instanciaExtraidas.contains(extractorAleatorioInstancias));
			
			//Añado la instancia extraida a las evaluadas.
			instanciaExtraidas.add(extractorAleatorioInstancias);
			//Establezco la instancia como centroide.
			this.centroides[indice]=this.getInstancias().getInstancia(extractorAleatorioInstancias);	
		}
		//Calculo las pertenencias para dejar la inicialización preparada.
		this.calcularPertenencias();
		
	}
	


	
}
