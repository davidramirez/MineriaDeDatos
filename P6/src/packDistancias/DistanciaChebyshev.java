package packDistancias;

import packInstancias.Instancia;

/**
 * Implementación de la distancia Chebyshev
 *
 */
public class DistanciaChebyshev extends Distancia {

	
	public double distancia(Instancia pInstancia1, Instancia pInstancia2)
			throws Exception {
		if(validar(pInstancia1, pInstancia2))
		{
			int dimension=pInstancia1.dimension();
			double distanciaMaxima=0;
			double distanciaActual=0;
			
			for(int i=0;i<dimension;i++)
			{
				distanciaActual=Math.abs(pInstancia1.getVector().get(i)-pInstancia2.getVector().get(i));
				if(distanciaActual>distanciaMaxima)
				{
					distanciaMaxima=distanciaActual;
				}
			}
			return distanciaMaxima;
		}
		else
		{
			throw new Exception("Vectores de tamaño distinto");
		}
	}

	/**
	 * 
	 * @param pInstancia1: Instancia primera.
	 * @param pInstancia2: Instancia segunda.
	 * @return Retorna true si es posible calcular la distancia entre dos instancias , false en caso contrario.
	 */
	private boolean validar(Instancia pInstancia1,Instancia pInstancia2) 
	{
		return pInstancia1.mismaDimension(pInstancia2);
	}

	@Override
	public String paramToString() {
		return "-d-Chebyshev";
	}
}
