package packLabo3;

public class StopWatch {
		
		private final long start;
		
		public StopWatch(){
			//Constructora, al ser invocada la clase mediante un new, almacena la hora de su creación.
			start=System.currentTimeMillis();
		}
		
		/**
		 * Método que devuelve el tiempo que ha pasado desde la creación de el objeto de esta clase
		 * y la ejecución de este método.
		 * @return
		 */
		public double elapsedTime(){
		long now=System.currentTimeMillis();
		return(now-start)/1000.0;
		
		}
	}
