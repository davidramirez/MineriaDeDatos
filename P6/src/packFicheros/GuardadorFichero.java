package packFicheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Clase encargada de la gestión de ficheros de salida de la aplicación
 *
 */
public class GuardadorFichero {

	private String extensionFicheroCargado;
	private static GuardadorFichero mGuardadorFichero = new GuardadorFichero();
	
	private GuardadorFichero(){}
	
	public static GuardadorFichero getGuardadorFichero()
	{
		return mGuardadorFichero;
	}
	
	/**
	 * Crea un nuevo fichero y devuelve su descriptor para escribir en el mismo
	 * @param pPathOriginal
	 * El path del fichero original, con extensión
	 * @param pSufijo
	 * El sufijo que tendrá el nuevo fichero
	 * @return
	 * El descriptor del fichero para comenzar su escritura. null si no se puede crear
	 */
	public PrintStream crearFichero(String pPathOriginal, String pSufijo)
	{
		String path = pPathOriginal.substring(0, (pPathOriginal.length() - this.extensionFicheroCargado.length()));
		path = path + pSufijo;
		
		File f = new File(path);
		PrintStream ps;
		try {
			ps = new PrintStream(f);
			return ps;
		} catch (FileNotFoundException e) {
			System.err.println("No se ha podido crear el fichero de guardado");
			System.exit(1);
		}
		
		return null;
	}
	
	public void setExtensionFicheroCargado(String pExtension)
	{
		this.extensionFicheroCargado = pExtension;
	}
}
