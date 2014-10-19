package packMain;

import java.io.PrintStream;

import packDistancias.Distancia;
import packDistancias.DistanciaChebyshev;
import packDistancias.DistanciaMinkowski;
import packFicheros.CargadorFichero;
import packFicheros.GuardadorFichero;
import packInstancias.ListaInstancias;
import packKMeans.DivisionEspacio;
import packKMeans.InicializacionAleatoria;
import packKMeans.InicializacionAleatoriaVariante;
import packKMeans.KMeans;
import packKMeans.PertenenciaAleatoria;

/**
 * Clase principal del proyecto con el método principal para la ejecución del programa
 *
 */
public class MDP6 {

	public static void main(String[] args) {
		
		if(args.length!=0)
		{
			/*Comprobar si se ha pedido la ayuda*/
			if(buscarParametro("-h", args) != -1)
			{
				//Se imprime y sale el programa
				help();
				System.exit(0);
			}
			
			//Objetos necesarios para el algoritmo y algunos de sus valores por defecto
			String ruta = null;
			ListaInstancias instancias = null;
			int k = 0;
			Distancia distancia = null;
			KMeans algoritmo = null;
			int numIteraciones = 0;
			double delta = 0;
			
			
			/******************** Bloque de carga de instancias ********************/
			ruta = args[0];
			
			instancias = CargadorFichero.getCargadorFichero().cargarFichero(ruta);
			
			
			
			/******************** Bloque de distancia ************************************/
			
			String tipoDistancia = args[buscarParametro("-d", args)+1];//obtenerParametro("-d", args);
			
			if(tipoDistancia != null)
			{
				//si se especifica una distancia, miramos cuál es
				if(tipoDistancia.equals("M"))
				{
					try {
						distancia = new DistanciaMinkowski(Double.parseDouble(args[buscarParametro("M", args)+1]));
					} catch (NumberFormatException e) {
						System.err.println("El parámetro m especificado para la distancia de Minkovski debe ser numérico");
						System.exit(1);
					} catch (Exception e) {
						System.err.println(e.getMessage());
						System.exit(1);
					}
				}
				else
					if(tipoDistancia.equals("C"))
					{
						distancia=new DistanciaChebyshev();
					}
					else
					{
						//No se sabe qué distancia ha especificado el usuario, salimos
						System.err.println("La especificación de la distancia es in correcta");
						help();
						System.exit(1);
					}
			}
			else
			{
				//no se ha especificado ninguna distancia, se usa por defecto la Euclidea
				try {
					distancia = new DistanciaMinkowski(2);
				} catch (Exception e) {}
			}

			/******************** Bloque param k ***********************************/
			
			int posK = buscarParametro("-k", args);
			
			if(posK != -1)
			{
				//obtenemos el numero de iteraciones a realizar
				try{
				k = Integer.parseInt(args[posK +1]);
				} catch (NumberFormatException e){
					System.err.println("El número de clusters debe ser especificado por un valor entero");
					System.exit(1);
				}
			}
			else
			{
				//no se ha especificado k
				System.err.println("Debe especificar el parámetro K para poder realizar la ejecución del programa");
				System.exit(1);
			}
			
			
			
			/******************** Bloque de iteraciones y delta ********************/
			int posIt = buscarParametro("-e", args);
			int posDelta = buscarParametro("-c", args);
			
			if(posIt != -1)
			{
				//obtenemos el numero de iteraciones a realizar
				try{
				numIteraciones = Integer.parseInt(args[posIt +1]);
				} catch (NumberFormatException e){
					System.err.println("El número de iteraciones debe ser especificado por un valor entero");
					System.exit(1);
				}
				
				//Si no se ha especificado un delta, este se considera infinito y el algoritmo finalizará cuando acaben las iteraciones
				if(posDelta == -1) delta = Double.MAX_VALUE;
			}
			
			if(posDelta != -1)
			{
				//obtenemos la delta indicada
				try{
					delta = Double.parseDouble(args[posDelta +1]);
					} catch (NumberFormatException e){
						System.err.println("El delta debe ser especificado por un valor real");
						System.exit(1);
					}
					
					//Si no se ha especificado un num de iteraciones, este se considera infinito y el algoritmo finalizará cuando la divergencia entre codebooks sea menor que delta
					if(posIt == -1) numIteraciones = Integer.MAX_VALUE;
			}
			
			if(posDelta == -1 && posIt == -1)
			{
				//si ninguno de los dos parámetros ha sido especificado, por defecto se asignan los valores:
				numIteraciones = 10;
				delta = 0.00001;
			}
			
			/******************** Bloque de inicialización ********************/
			
			int posIncializacion = buscarParametro("-i", args);
			
			if(posIncializacion != -1)
			{
				//si se ha especificado el tipo de inicialización, lo detectamos
				switch (args[posIncializacion+1]) {
				case "aleatoria":
					algoritmo = new InicializacionAleatoria(k, distancia, instancias, numIteraciones, delta);
					break;
				case "pert_aleatoria":
					algoritmo = new PertenenciaAleatoria(k, distancia, instancias, numIteraciones, delta);
					break;
				case "cent_aleat":
					algoritmo = new InicializacionAleatoriaVariante(k, distancia, instancias, numIteraciones, delta);
					break;
				case "particionada":
					algoritmo = new DivisionEspacio(k, distancia, instancias, numIteraciones, delta);
					break;

				default:
					//no se reconoce la especificación
					System.err.println("No se reconoce el tipo de inicialización especificado: "+args[posIncializacion+1]);	
					help();
					System.exit(1);
					break;
				}
			}
			else
			{
				// Si no se ha especificado, por defecto usamos "aleatoria"
				algoritmo = new InicializacionAleatoria(k, distancia, instancias, numIteraciones, delta);
			}
			
			/***************************Preparar ficheros de salida******************/
			
			PrintStream salidaInf = GuardadorFichero.getGuardadorFichero().crearFichero(ruta, "-inf.txt");
			PrintStream salidaEstimacion = GuardadorFichero.getGuardadorFichero().crearFichero(ruta, "-etiquetado.csv");
			
			
			/***************************Bloque ejecución******************************/
			//En este punto ya podemos ejecutar el algoritmo
			algoritmo.ejecutar(salidaInf);
			algoritmo.guardarInstanciasClasificadas(salidaEstimacion, concatenarParametros(args));
			
			//cerramos los ficheros
			salidaEstimacion.close();
			salidaInf.close();

		}
		else
		{
			System.err.println("No se han especificado parámetros, el programa no puede continuar");
			help();
			System.exit(1);
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
	 * Devuelve true si el parámetro que se busca está incluido en los argumentos.
	 * @param pParametro
	 * @param pArgumentos
	 * @return
	 */
	private static boolean existeParametro(String pParametro,String[] pArgumentos)
	{
		return buscarParametro(pParametro, pArgumentos)==-1?false:true;
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
	
	/**
	 * Imprime la ayuda del programa
	 */
	private static void help()
	{
		
	}
	
	private static String concatenarParametros(String[] param)
	{
		String parametros = new String();
		
		for(int i = 0; i < param.length; i++)
		{
			parametros = parametros+param[i] + " ";
		}
		return parametros;
	}
}
