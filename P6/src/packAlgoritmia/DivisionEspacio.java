package packAlgoritmia;

import packDistancias.Distancia;
import packFicheros.CargadorFichero;
import packInstancias.ListaInstancias;

public class DivisionEspacio extends KMeans {

	public DivisionEspacio(int pK, Distancia pDistancia, ListaInstancias pListaInstancias, int pNumIt, double pDelta) {
		super(pK, pDistancia, pListaInstancias, pNumIt, pDelta);
	}

	@Override
	protected void inicializar() {
		// TODO Auto-generated method stub
	}

}
