package packAlgoritmia;

import javax.print.attribute.standard.PrinterResolution;

import packDistancias.Distancia;
import packDistancias.DistanciaChebyshev;
import packDistancias.DistanciaMinkowski;
import packFicheros.CargadorFichero;

public class MDP6 {

	public static void main(String[] args) {
		
		if(args.length!=0)
		{
			
			/* Bloque de carga de ruta. */
			String ruta=args[0];
			
			CargadorFichero.getCargadorFichero().cargarFichero(ruta);
			
			/*Bloque de distancia*/
			Distancia distancia;
			
			String tipoDistancia=obtenerParametro("-d", args);
			
			if(tipoDistancia.equals("M"))
			{
				distancia=new DistanciaMinkowski(Double.parseDouble(args[buscarParametro("M", args)+1]));
			}
			else
				if(tipoDistancia.equals("C"))
				{
					distancia=new DistanciaChebyshev();
				}
				else
				{
					System.exit(-2);
				}


		}

	}

	/**
	 * Dado un string, se busca su posición dentro del array
	 * @param pStr 
	 * @param args
	 * el array en el que buscar
	 * @return
	 * la posición del string dentro del array
	 * -1 en caso de que no se encuentre el elemento
	 */
	private static int buscarParametro(String pStr, String[] array) {
		//comprobamos que el array tenga elementos
		if(array.length != 0)
		{
			//recorremos el array buscando el elemento
			
			int i = 0;
			boolean enc = false;
			
			while(!enc && i < array.length)
			{
				if(array[i].equals(pStr))
				{
					enc = true;
				}
				else
				{
					i++;
				}
			}
			
			if(!enc)
			{
				i = -1;
			}
			
			return i;
		}
		return -1;
	}
	
	/**
	 * Dada la ruta de un fichero
	 * comprueba si el fichero tiene la extensión indicada por parámetro
	 * @param fichero
	 * La ruta del fichero a comprobar
	 * @param extension
	 * La extensión de la que tiene que ser el fichero
	 * @return
	 * TRUE si el fichero es del tipo de extensión indicado
	 * FALSE si es nulo o la extensión no se corresponde
	 */
	private static boolean comprobarExtension(String ruta, String extension) {
		
		if(ruta != null)
		{
			//el nombre debe ser más largo que la extensión
			if(ruta.length() > extension.length())
			{
				//debug
				//System.out.println(fichero.substring((fichero.length() - extension.length()),fichero.length()));
				
				//extraemos del string del fichero la extensión y comparamos
				if(ruta.substring((ruta.length() - extension.length()),ruta.length()).compareTo(extension)  == 0)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Devuelve true si el parámetro que se busca está incluido en los argumentos.
	 * @param pParametro
	 * @param pArgumentos
	 * @return
	 */
	private static boolean existeParametro(String pParametro,String[] pArgumentos)
	{
		return buscarParametro(pParametro, pArgumentos)==-1?true:false;
	}
	
	/**
	 * Devuelve el parámetro que se está buscando dentro del array de argumentos si existe
	 * @param pParametro
	 * @param pArgumentos
	 * @return
	 */
	private static String obtenerParametro(String pParametro,String[] pArgumentos)
	{
		if(existeParametro(pParametro, pArgumentos))
		{
			return pArgumentos[buscarParametro(pParametro, pArgumentos)];
		}
		else
		{
			return null;
		}
	}
}
