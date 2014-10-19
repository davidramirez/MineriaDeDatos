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
				
				if(k < 1)
				{
					//si la k no es correcta, lanzamos la misma excepción (eltratamiento es igual)
					throw new NumberFormatException();
				}
				} catch (NumberFormatException e){
					System.err.println("El número de clusters debe ser especificado por un valor natural");
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
			
			//finalizamos el programa
			System.exit(0);

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
	@SuppressWarnings("unused")
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
		System.out.println("Comando: java -jar MDP6.jar Fichero_Instancias -k num [-d (M m|C)] [-i (aleatoria|pert_aleatoria|cent_aleat|particionada)] [-e num] [-c num] [-h]"
				+ "\n\nLeyenda:"
				+ "\n	parametro					- parámetro de especificación obligatoria"
				+ "\n	[parámetro]					- parámetro de especificación opcional"
				+ "\n	param (opc1|opc2|..|opn)	- diferentes opciones para un parámetro, elegir una de ellas. Ej.: param opc2"
				+ "\n\nArgumentos:"
				+ "\n\n	Fichero_Instancias"
				+ "\n		Fichero que contiene el conjunto de datos a utlilizar. En formato csv"
				+ "\n\n	-k num"
				+ "\n		Establece a num (valor natural) el número de clusters que se crearan en la ejecución del algoritmo"
				+ "\n\n	[-d (M m|C)]"
				+ "\n		Establece la métrica a utilizar en el desarrollo del algoritmo. Si no se especifica se usa por defecto la Euclidea"
				+ "\n\n			Posibilidades:"
				+ "\n				M m	- Distancia Minkowski con la especificación del parámetro m"
				+ "\n				C	- Distancia Chebyshev"
				+ "\n\n	[-i (aleatoria|pert_aleatoria|cent_aleat|particionada)]"
				+ "\n		Establece el tipo de inicialización a utilizar en el algoritmo K-means. Si no se especifica, por defecto se utiliza la inicialización aleatoria"
				+ "\n\n			Posibilidades"
				+ "\n				aleatoria		- Elige al azar k instancias del conjunto de datos para actuar de centroides"
				+ "\n				pert_aleatoria	- Por cada instancia, la incluye al azar en uno de los k clusters. Posteriormente se calculan los centroides"
				+ "\n				cent_aleat		- Se recogen los rangos en que varían los atributos de todas las instancias. Posteriromente se crean k centrroides con valores al azar incluidos en los diferentes intervalos identificados"
				+ "\n				particionada	- Se divide el espacio muestrald de acuerdo al número de clusters a crear y se identifican los centroides"
				+ "\n\n	[-e num]"
				+ "\n		Establece el número de iteraciones del algoritmo."
				+ "\n\n	[-c num]"
				+ "\n		Establece la delta usada para comparar la convergencia del error obtenido en dos iteraciones sucesivas (su variación permitida)"
				+ "\n\n	Si no se indican ni e ni c, se consideran los valores por defecto 10 y 0.00001. En caso de que solo se especifique uno de ellos, únicamente ese se tendrá en cuenta como criterio de parada del algoritmo"
				+ "\n\n	[-h]"
				+ "\n		imprime la ayuda para la ejecución del programa. Si este parámetro se especifica, finaliza el programa tras mostrar la ayuda, independientemente del resto de parámetros especificados");
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
