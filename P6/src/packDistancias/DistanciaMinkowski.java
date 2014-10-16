package packDistancias;

import packInstancias.Instancia;

public class DistanciaMinkowski extends Distancia {

	private double exponente;
	
	
	public DistanciaMinkowski(double pExponente) throws Exception
	{
		if(pExponente>=1)
		{
			this.setExponente(pExponente);
		}
		else
		{
			throw new Exception("Exponente inválido, el parámetro debe ser >= 1");
		}
	}
	
	public double distancia(Instancia pInstancia1, Instancia pInstancia2) throws Exception 
	{
		if(validar(pInstancia1, pInstancia2))
		{
			double acumuladorSumatorio=0;
			int dimensiones=pInstancia1.getVector().size();
			
			for(int i=0;i<dimensiones;i++)
			{
				acumuladorSumatorio+=Math.pow(Math.abs((pInstancia1.getVector().get(i)-pInstancia2.getVector().get(i))),this.getExponente());
			}
			return Math.pow(acumuladorSumatorio,this.getExponente());
		}
		else
		{
			throw new Exception("Vectores de tamaño distinto");
		}
			
	}
	
	/**
	 * 
	 * @param pInstancia1: Instancia primera.
	 * @param pInstancia2: Instancia segura.
	 * @return Retorna true si es posible calcular la distancia entre dos instncias , false en caso contrario.
	 */
	private boolean validar(Instancia pInstancia1,Instancia pInstancia2) 
	{
		return pInstancia1.mismaDimension(pInstancia2);
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	public double getExponente() {
		return exponente;
	}

	public void setExponente(double exponente) {
		this.exponente = exponente;
	}


	

}
