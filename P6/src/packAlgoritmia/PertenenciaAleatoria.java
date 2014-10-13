package packAlgoritmia;

import packDistancias.Distancia;
import packInstancias.ListaInstancias;

public class PertenenciaAleatoria extends KMeans {

	public PertenenciaAleatoria(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta) {
		super(pK, pDistancia, pListaInstancias, pNumIt, pDelta);
	}

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
		
	}

}
