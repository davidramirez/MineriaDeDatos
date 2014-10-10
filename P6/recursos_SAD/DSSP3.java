package packLabo3;

public class DSSP3 {

	//TODO Redondear todos los dobles a 3 decimales
	/**
	 * Códigos de error:
	 * 1 - fallo del fichero arff de datos
	 * 2 - fallo al especificar el fichero de test
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Las comprobaciones las haremos si tenemos parámetros, si no es imposible la ejecución
		if(args.length != 0)
		{
			//variables correspondientes a los parámetros de la llamada
			//por defecto toman los siguientes valores
			String ficheroDatos = null;//fichero fuente (path)
			String ficheroTest = null;//fichero de test (path)
			boolean escanearORule = true;//barrer ORule
			boolean escanearSVM = true;//barrer SVM (por defecto se hacen los dos)
			boolean informacionExtra = false;//imprimir información extra referente a las evaluaciones
			
			
			//A continuación examinamos los parámetros de la llamada y establecemos los parámetros indicados por el usuario
			
			
			//el primer parámetro es obligatorio que sea un fichero  arff
			if(!comprobarExtension(args[0], ".arff"))
			{
				//si no la cumple, error y mostramos las opciones válidas
				mostrarOpciones();
				System.exit(1);
			}
			else
			{
				//establecemos la ruta del fichero
				ficheroDatos = args[0];
				System.out.println("Usando el fichero: " + ficheroDatos);
				
				
				//ahora comprobamos y establecemos el  resto de parámetros opcionales
				
				//***********************************opciones de Test
				//con el parámetro "-t" se nos da un fichero arff con las instancias a predecir la clase
				int i = buscarParametro("-t", args);//buscamos su posición
				
				//Si lo encontramos
				if(i != -1)
				{
					//comprobamos que el siguiente parámetro es un archivo es un arff
					//no nos tenemos que salir de rango del array
					if(i+1 < args.length)
					{
						//comprobar extensión
						if(!comprobarExtension(args[i+1], ".arff"))
						{
							//si no la cumple, error y mostramos las opciones válidas
							System.out.println("Debe especificar un fichero .arff con el parámetro -t");
							mostrarOpciones();
							System.exit(2);
						}
						else
						{
							//Establecemos el path del fichero de test que nos han indicado
							ficheroTest = args[i+1];
							System.out.println("Se estimará la clase de las instancias del fichero: " + ficheroTest);
						}
					}
					else
					{
						System.out.println("Debe especificar un fichero .arff con el parámetro -t");
						mostrarOpciones();
						System.exit(2);
					}
				}
				
				//***********************************opciones de escaneo
				//con el parámetro -ORule se indica que se escaneará OneRule
				int j = buscarParametro("-ORule", args);
				
				if(j!=-1)
				{
					System.out.println("Se ejecutará un barrido para el clasificador OneRule");
					//se modifican las opciones de escaneo
					escanearORule = true;
					escanearSVM = false;
				}
				//con el parámetro -SVN se indica que se escaneará SVM
				int k = buscarParametro("-SVM", args);
				
				if(k!=-1)
				{
					System.out.println("Se ejecutará un barrido para el clasificador SVM");
					//se modifican las opciones de escaneo
					escanearORule = false;
					escanearSVM = true;
				}
				
				//si se han indicado ambos, barreremos los dos
				if(j!=-1 && k!=-1)
				{
					escanearORule = true;
					escanearSVM = true;
				}
				
				//*************************************opción de información extra (imprimir estimaciones)
				//Con el parámetro -inf se indica que se deben imprimir las estimaciones
				int l = buscarParametro("-inf", args);
				
				if(l!=-1)
				{
					System.out.println("Se imprimirá la información relativa a las distintas estimaciones realizadas");
					informacionExtra = true;
				}
				
				
				//**********************************************************************************//
				//Una vez comprobadas las distintas opciones de la llamada, lanzamos el programa    //
				//**********************************************************************************//
				
				//lanzamos en onerule si se requiere
				if(escanearORule)
				{
					//al construir el objeto le pasamos los parámetros necesarios (si tiene que testear, generar información...)
					ScanParamsOneR oRule = new ScanParamsOneR(ficheroDatos, ficheroTest, informacionExtra);
					
					//lanzamos el barrido
					oRule.barrerParametros();
				}
				
				//lanzamos el SVM si se requiere
				if(escanearSVM)
				{
					//al construir el objeto le pasamos los parámetros necesarios (si tiene que testear, generar información...)
					ScanParamsAlgorithm svm = new ScanParamsAlgorithm(ficheroDatos, ficheroTest, informacionExtra);
					
					//lanzamos el barrido
					svm.barrerParametros();
				}
			}
		}
		else
		{
			//Si no hay ningún parámetro, se mostrarán las opciones que proporciona el programa
			mostrarOpciones();
		}

	}

	/**
	 * Imprime por consola las opciones de llamada del ejecutable
	 */
	private static void mostrarOpciones() {
		System.out.println("java -jar DSSP3.jar RUTA_FICHERO/ficheroDatos.arff [OPCIONES]");
		System.out.println();
		System.out.println("Argumentos:");
		System.out.println();
		System.out.println("\t 1. RUTA_FICHERO/ficheroDatos.arff: path del fichero arff fuente a utilizar en el algoritmo");
		System.out.println("\t 2. [OPCIONES]: Todas ellas son opcionales. El orden utilizado para escribirlas es indiferente siempre y cuando se sigan las especificaciones concretas de cada una.");
		System.out.println("\t\t -ORule: Se ejecutarán barrido para el clasificador OneRule.");
		System.out.println("\t\t -SVM: Se ejecutarán barrido para el clasificador SVM.\n\t\t\t + NOTA: Si no se especifica ninguna de las opciones anteriores, por defecto se llevará cabo ambos barridos, surtiendo el mismo efecto que como si se especificaran ambas a la vez. ");
		System.out.println("\t\t -inf: Si se especifica el comando se generarán fichero txt en la misma carpeta que el arff especificado (acabado en -inf.txt) con la clasificación de todas las combinaciones de parátros evaluadas por el algoritmo que hayan resultado satisfactorias. Las evaluaciones se muestran ordenadas siguiendo un criterio de ordenación");
		System.out.println("\t\t -t [RUTA_FICHERO/ficheroTest.arff]: Es necesario que tenga la misma estructura que el fichero arff fuente. Es indiferente si la clase es conocida o no.");
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
	 * Dado el path de un fichero
	 * comprueba si el fichero tiene la extensión indicada por parámetro
	 * @param fichero
	 * La ruta del fichero a comprobar
	 * @param extension
	 * La extensión de la que tiene que ser el fichero
	 * @return
	 * TRUE si el fichero es del tipo de extensión indicado
	 * FALSE si es nulo o la extensión no se corresponde
	 */
	private static boolean comprobarExtension(String fichero, String extension) {
		
		if(fichero != null)
		{
			//el nombre debe ser más largo que la extensión
			if(fichero.length() > extension.length())
			{
				//debug
				//System.out.println(fichero.substring((fichero.length() - extension.length()),fichero.length()));
				
				//extraemos del string del fichero la extensión y comparamos
				if(fichero.substring((fichero.length() - extension.length()),fichero.length()).compareTo(extension)  == 0)
				{
					return true;
				}
			}
		}
		return false;
	}

}
