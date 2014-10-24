package packMain;

import java.io.PrintStream;

import packDistancias.Distancia;
import packDistancias.DistanciaChebyshev;
import packDistancias.DistanciaMinkowski;
import packFicheros.CargadorFichero;
import packFicheros.GuardadorFichero;
import packInstancias.ListaInstancias;
import packKMeans.KMeans;

public class MDP6_tab_errores {

	public static void main(String[] args) {
		//parámetros del main, el fichero a usar y la función de distancia
		
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
			Distancia distancia = null;
			
			/******************** Bloque de carga de instancias ********************/
			ruta = args[0];
			
			ListaInstancias instancias = CargadorFichero.getCargadorFichero().cargarFichero(ruta);
			
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
			
			/**********************Preparamos los parámetros a variar*****************************/
			
			int numRepeticiones = 100;//repeticiones totales por cada ejecución posible
			int[] k = {2,3,5,7,9};//Parámetro k a usar por defecto para cada posible ejecución
			String[] inicializacion = {"aleatoria","pert_aleatoria","cent_aleatoria","particionada"}; //etiquetas de las inicializaciones a utilizar
			
			/************************************Parámetros  por defecto del algoritmo**********************/
			KMeans algoritmo = null;
			int numIteraciones = 100;
			double delta = 0.001;
			
			/**************************Fichero de salida***********************************/
			PrintStream fichero;
			//creamos cada fichero para escritura y le ponemos las columnas del contenido
			fichero = GuardadorFichero.getGuardadorFichero().crearFichero(ruta, "tabla-errores"+distancia.paramToString()+".txt");
			fichero.println("Error Cuadrático \t Inic_aleatoria \t Pertenencia_aleatoria \t Cent_aleatorios \t División_espacio");
			
			
			/*********************************Bloque ejecución*******************************/
		}

	}
	
	private static void help() {
		// TODO Auto-generated method stub
		
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


}
