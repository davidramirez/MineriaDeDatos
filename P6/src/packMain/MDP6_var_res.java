package packMain;

import java.io.PrintStream;

import packDistancias.Distancia;
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
 * Destinado a la obtención de resultados para demostrar la variabilidad de los mismos en sucesivas ejecuciones por el uso de aleatorios no mapeados
 *
 */
public class MDP6_var_res {

	
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
			
			/******************** Bloque de carga de instancias ********************/
			ruta = args[0];
			
			ListaInstancias instancias = CargadorFichero.getCargadorFichero().cargarFichero(ruta);
			
			/**********************Preparamos los parámetros a variar*****************************/
			
			int numRepeticiones = 10000;//repeticiones totales por cada tipo de inicializacion
			int[] k = {3,5,8};//PArametro k a usar por defecto en cada inicializacion
			String[] inicializacion = {"aleatoria","pert_aleatoria","cent_aleatoria","particionada"}; //etiquetas de las inicializaciones a utilizar
			
			/**************************Ficheros de salida***********************************/
			PrintStream[] ficheros = new PrintStream[k.length];//los diferentes ficheros a crear, un por cada valor de k
			
			for(int i=0;i<ficheros.length;i++)
			{
				//creamos cada fichero para escritura y les ponemos las columnas del contenido
				ficheros[i] = GuardadorFichero.getGuardadorFichero().crearFichero(ruta, "variaciones-k-"+k[i]+".txt");
				ficheros[i].println("Iteración \t Inic_aleatoria \t Pertenencia_aleatoria \t Cent_aleatorios \t División_espacio");
			}
			
			/************************************Parámetros  por defecto del algoritmo**********************/
			KMeans algoritmo = null;
			
			Distancia distancia = null;
			try {
				distancia = new DistanciaMinkowski(2.0);//distEuclidea
			} catch (Exception e) {
				e.printStackTrace();
			}
			int numIteraciones = 100;
			double delta = 0.001;
			
			
			/************************Variacion de parámetros************************************/
			
			for(int vuelta = 1; vuelta <= numRepeticiones;vuelta++)
			{
				//por cada vuelta
				for(int fich=0;fich < ficheros.length;fich++)//variacion de la k y el fichero, ambos se corresponden
				{
					//Sevaria la k del algoritmo
					
					//imprimo el num de vuelta en el fichero
					ficheros[fich].print(vuelta);
					
					for(int inic=0;inic < inicializacion.length;inic++)
					{
						//y su tipo de inicialización según corresponda
						switch (inicializacion[inic]) {
						case "aleatoria":
							algoritmo = new InicializacionAleatoria(k[fich], distancia, instancias, numIteraciones, delta);
							break;
						case "pert_aleatoria":
							algoritmo = new PertenenciaAleatoria(k[fich], distancia, instancias, numIteraciones, delta);
							break;
						case "cent_aleat":
							algoritmo = new InicializacionAleatoriaVariante(k[fich], distancia, instancias, numIteraciones, delta);
							break;
						case "particionada":
							algoritmo = new DivisionEspacio(k[fich], distancia, instancias, numIteraciones, delta);
							break;
						}
							
							/**************************una vez tenemos un algoritmo listo********/
							//lo ejecutamos
							algoritmo.ejecutar(System.out);//no nos interesa lo que imprime el algoritmo, lo dirigimos a la salida estandar
							
							//Cogemos el valor de el error cuadrático medio para esta ejecucion y lo miprimimos en el fichero correspondiente
							ficheros[fich].print("\t"+algoritmo.getECM());
							
					}
					
					//para la siguiente vuelta de cada fichero, salto de línea
					ficheros[fich].println();
				}
			}
			
			/*******************************Cerramos los ficheros********************/
			for(int i=0;i<ficheros.length;i++)
			{
				ficheros[i].close();
			}
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
