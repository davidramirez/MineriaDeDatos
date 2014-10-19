package packFicheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import packInstancias.Instancia;
import packInstancias.ListaInstancias;

public class CargadorFichero {
	
	private static CargadorFichero mCargadorFichero = new CargadorFichero();
	
	String ficherosSoportados = ".csv";
	
	private CargadorFichero()
	{
		
	}
	
	public static CargadorFichero getCargadorFichero()
	{
		return mCargadorFichero;
	}
	
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
			//Le decimos al guardador fichero la extensión de fichero que hemos cargado
			GuardadorFichero.getGuardadorFichero().setExtensionFicheroCargado(".csv");
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
			//lista a cargar
			ListaInstancias lista = new ListaInstancias();
			
			File fichero = new File(path);
			Scanner sc = new Scanner(fichero);
			String linea = null;
			
			//comenzamos a cargar el fichero
			//Puede haber comentarios en las primeras líneas o lineas vacías hasta encontrar la linea que da nombre a los atributos
			boolean masComentarios = true;
			while(masComentarios && sc.hasNext())
			{
				linea = obtenerLineaSinComentario(sc.nextLine());
				
				if(linea.length() > 0)
				{//si la linea no está vacía
					if(linea.matches("\\S+"))
					{//si la línea tiene algún caracter y no empieza por %
						//hemos encontrado la primera línea válida
						masComentarios = false;
					}
				}
			}
			
			//ya tenemos la primera linea con los nombres de los atributos
			String[] atributos = obtenerLineaSinComentario(linea).split(",");
			lista.setNombresAtributos(atributos);
			
			//obtenemos la dimension de las instancias
			int dimension = atributos.length;
			//y la asignamos a la lista de instancias
			lista.setDimension(dimension);
			
			//ahora procesamos el resto del fichero añadiendo cada una de las instancias
			while(sc.hasNext())
			{
				//obtenemos la siguiente linea
				linea = obtenerLineaSinComentario(sc.nextLine());
				
				if(linea.length() > 0)
				{
					if(linea.matches("\\S+"))
					{//tiene caracteres
						String[] instancia = linea.split(",");
						
						//procesamos la instancia
						if(instancia.length == dimension)
						{
							//obtenemos cada uno de los atributos y los convertimos a double
							Vector<Double> valores = new Vector<Double>();
							
							for(int i = 0; i<instancia.length;i++)
							{
								try{
									valores.add(i, Double.parseDouble(instancia[i]));
								}catch(NumberFormatException e)
								{
									//TODO mejorar, evitar el error y quedarse automáticamente con los atributos numéricos descartando el resto
									System.err.println("El fichero contiene instancias con atributos no numéricos");
									System.exit(1);
								}
								
							}
							
							Instancia instanciaCargada = new Instancia(valores);
							
							lista.anadirInstancia(instanciaCargada);
						}
						else
						{
							//si la dimensión de la instancia no coincide con la especificada por los nombres de los atributos
							//no tenemos en cuenta la instancia y seguimos
							System.err.println("La dimensión de la instancia: "+linea+" no coincide la dimensión especificada: "+dimension+" y no ha sido cargada");
						}
					}
				}
			}
			
			//carga terminada
			return lista;
			
			
			
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

	
	/**
	 * Dada una línea de un fichero, devuelve la parte útil de la misma, es decir, hasta que se encuentra un comentario
	 * indicado por '%'
	 * @param pLinea
	 * Linea a procesar
	 * @return
	 * La linea sin comentarios, vacía si el  comentario está al principio de la misma
	 */
	private String obtenerLineaSinComentario(String pLinea)
	{
		int indComent = pLinea.indexOf("%");
		
		if(indComent == 0)
		{
			return new String();//comentario al principio de la línea
		}
		if(indComent != -1)
		{
			//si la línea tiene un comentario, devolvemos la línea sin el comentario
			return pLinea.substring(0, indComent -1);
		}
		else
		{
			return pLinea;
		}
	}

}
