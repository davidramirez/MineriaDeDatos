********************************** README DSSP3.jar **********************************

Objetivo: 
	
	Obtener los mejores parametros (de acuerdo al criterio de ordenación definido más adelante) para un clasificador OneR (opción ORule) y un clasificador SVM (opción SVM) con 5-fold cross-validation.
	A su vez, se obtendrá tanto su cota optimista como su cota realista.
	Para el OneR, se repetirá el proceso utilizando la librería CVParamsSelection para posteriormente hacer un análisis comparativo.
	Opcionalmente, se recogen las evaluaciones realizadas y realizarán las predicciones de un test con los mejores clasificadores hallados en cada caso.


Ejecucion:

	Comando: java -jar DSSP3.jar RUTA_FICHERO/ficheroDatos.arff [OPCIONES]

	Argumentos:
		1. RUTA_FICHERO/ficheroDatos.arff: path del fichero arff fuente a utilizar en el algoritmo
		2. [OPCIONES]: Todas ellas son opcionales. El orden utilizado para escribirlas es indiferente siempre y cuando se sigan las especificaciones concretas de cada una. 
			-ORule: Se ejecutará el barrido para el clasificador OneRule.
			-SVM: Se ejecutará el barrido para el clasificador SVM.
				+ NOTA: Si no se especifica ninguna de las opciones anteriores, por defecto se llevarían a cabo ambos barridos, surtiendo el mismo efecto que como si se especificaran ambas a la vez. 
			-inf: Si se especifica el comando se generará el fichero txt en la misma carpeta que el arff especificado (acabado en -inf.txt) con la clasificación todas las combinaciones de parámetros evaluadas por el algoritmo que hayan resultado satisfactorias. Las evaluaciones se muestran ordenadas siguiendo un criterio de ordenación.
			-t [RUTA_FICHERO/ficheroTest.arff]: Es necesario que tenga la misma estructura que el fichero arff fuente. Es indiferente si la clase es conocida o no. 

Precondiciones:

	Para el desarrollo se ha utilizado el entorno Java SDK 1.7, Weka 3.6 y LibSVM 3.18. Se requiere que el usuario disponga de una máquina virual de JAVA versión 7. Las librerías Weka y LibSVM van incluidas dentro del mismo ejecutable.		
	El fichero arff es un fichero válido (su información es coherente y está correctamente estructurado) y las instancias tienen atributos numéricos y clase nominal.
	**Se considerará que la clase a predecir y evaluar es siempre el último atributo especificado en el fichero. 

Postcondiciones:
	
	Se crearán una serie de ficheros como consecuencia de la ejecución dependiendo de los parámetros provistos como argumento. 

	Para el OneR: 
		1. El fichero obligatorio: nombre_inicialUpperBoundsOneR.txt almacena la estimación optimista.
		2. El fichero obligatorio: nombre_inicialRealisticBoundsOneR.txt almacena la estimación realista.
		3. El fichero opcional: nombre_inicialTestPredictionsOneR-Adhoc.arff almacena la predicción del mejor modelo del barrido adhoc para las instancias indicadas por el test.
		4. El fichero opcional: nombre_inicialTestPredictionsOneR-CVParam.arff almacena la predicción del mejor modelo obtenido por CVParameters para las instancias indicadas por el test.
		5. El fichero opcional:nombre_inicial-inf-OneR.txt almacena los diferentes parámetros examinados ordenados según su utilidad.

	Para el SVM: 
		1. El fichero obligatorio: nombre_inicialUpperBoundsAlgorithm.txt almacena la estimación optimista.
		2. El fichero obligatorio: nombre_inicialRealisticBoundsAlgorithm.txt almacena la estimación realista.
		3. El fichero opcional: nombre_inicialTestPredictionsAlgorithm-Adhoc.arff almacena la predicción del mejor modelo del barrido adhoc para las instancias indicadas por el test.
		4. El fichero opcional: nombre_inicialTestPredictionsAlgorithm-CVParam.arff almacena la predicción del mejor modelo obtenido por CVParameters para las instancias indicadas por el test.
		5. El fichero opcional:nombre_inicial-inf-Algorithm.txt almacena los diferentes parámetros examinados ordenados según su utilidad.
	
	Se muestran por pantalla los parámetros de el clasificador que ha sido seleccionado como mejor valorado siguiendo el criterio de ordenación para cada barrido realizado.
	
	NOTA: Dentro de los ficheros, se utiliza salto de carro UNIX. Esto puede provocar que los ficheros se muestren de forma incorrecta en determinados lectores de texto. 
	Se recomienda el uso de Notepad++ para su visualización. 

Criterio de ordenación

	-Si la diferencia entre las f-measures de dos clasificadores (el clasificador queda definido por sus parámetros) es mayor que 0.01, el clasificador con mejor f-measure es mejor.
	-Si no, el que haya tardado menos tiempo es mejor.
	-Si han tardado lo mismo, el que mejor f-measure tenga es mejor.

Procedimiento: 
	
	Se aplican tanto el filtro Randomize (semilla 42 por defecto) como el k-Fold Cross Validation (semilla 42), siempre con la misma semilla para poder reproducir los resultados obtenidos en posteriores ejecuciones. 
	A la hora de ejecutar el SVM, la carga de los datos requiere de la aplicación del filtro Normalize para normalizar las instancias (tanto de entrenamiento como de test).
	
Ejemplos:

	Se proporcionan en el anexo "ExperimentalResults.tgz", concretamente los resultados que se obtendrán serán los del directorio "resultados_finales" del anterior archivo.
	
	A destacar que solo las pruebas de barrido del clasificador OneR pueden repetirse de manera fiel (teniendo en cuenta que el tiempo de ejecución también dependerá del sistema utilizado).
	Sin embargo, LibSVM no permite repetir de forma fiel las evaluaciones, por lo que estos resultados pueden tener variaciones respecto a los proporcionados.


	
