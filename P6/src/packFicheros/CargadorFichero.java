package packFicheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import packAlgoritmia.ListaInstancias;

public class CargadorFichero {
	
	String ficherosSoportados = ".csv";
	
	/**
	 * Dado el path de un fichero, comprueba que el fichro puede ser cargado (por su extensión) 
	 * y ejecuta el cargador correspondiente. Si el tipo de fichero no está soportado, finalizala ejecución del 
	 * programa
	 * @param path
	 * Archivo a cargar
	 * @return
	 * El fichero cargado en memoria como una ListaInstancias
	 */
	public ListaInstancias cargarFichero(String path)
	{
		ListaInstancias lista = null;
		
		if(comprobarExtension(path, ".csv"))
		{
			lista = cargarCSV(path);
		}
		else
		{
			System.out.println("El fichero especificado no puede ser cargado");
			System.out.println("Los ficheros soportados son:" + ficherosSoportados);
			System.exit(1);
		}
		
		return lista;
	}
	
	/**
	 * Dado un fichero cuya extensión indique que se trara de un CSV, procederá a su carga.
	 * @param path
	 * Fichero CSV a cargar
	 * @return
	 * Las instancias que contiene el fichero como una ListaInstancias
	 */
	private ListaInstancias cargarCSV(String path) {
		try {
			ListaInstancias lista = new ListaInstancias();
			
			File fichero = new File(path);
			Scanner sc = new Scanner(fichero);
			String linea;
			
			//comenzamos a cargar el fichero
			//Puede haber comentarios en las primeras líneas o lineas vacías hasta encontrar la linea que da nombre a los atributos
			boolean masComentarios = true;
			while(masComentarios)
			{
				linea = sc.nextLine();
				
				if(linea.length() > 0)
				{//si la linea no está vacía
					if(linea.matches("\\S") && !linea.matches("^%"))
					{//si la línea tiene algún caracter y no empieza por %
						//hemos encontrado la primera línea válida
						masComentarios = false;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero especificado no existe");
			System.exit(1);
		}
		
		return null;
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
	private boolean comprobarExtension(String fichero, String extension) {
		
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
