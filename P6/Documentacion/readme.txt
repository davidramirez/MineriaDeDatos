************************************README MDP6.jar************************************

Objetivo:
	Realizar la exploración de un conjunto de muestras mediante el algoritmo K-means clustering
	

Ejecución:

	Comando: java -jar MDP6.jar Fichero_Instancias -k num [-d (M m|C)] [-i (aleatoria|pert_aleatoria|cent_aleat|particionada)] [-e num] [-c num] [-h]
	
	Leyenda:
		parametro					- parámetro de especificación obligatoria
		[parámetro]					- parámetro de especificación opcional
		param (opc1|opc2|..|opn)	- diferentes opciones para un parámetro, elegir una de ellas. Ej.: param opc2
	
	Argumentos:
	
		Fichero_Instancias
			Fichero que contiene el conjunto de datos a utlilizar. En formato csv
			
		-k num
			Establece a num (valor natural) el número de clusters que se crearan en la ejecución del algoritmo
		
		[-d (M m|C)]
			Establece la métrica a utilizar en el desarrollo del algoritmo. Si no se especifica se usa por defecto la Euclidea
				
				Posibilidades:
					M m	- Distancia Minkowski con la especificación del parámetro m
					C	- Distancia Chebyshev
				
		[-i (aleatoria|pert_aleatoria|cent_aleat|particionada)]
			Establece el tipo de inicialización a utilizar en el algoritmo K-means. Si no se especifica, por defecto se utiliza la inicialización aleatoria
			
				Posibilidades
					aleatoria		- Elige al azar k instancias del conjunto de datos para actuar de centroides
					pert_aleatoria	- Por cada instancia, la incluye al azar en uno de los k clusters. Posteriormente se calculan los centroides
					cent_aleat		- Se recogen los rangos en que varían los atributos de todas las instancias. Posteriromente se crean k centrroides con valores al azar incluidos en los diferentes intervalos identificados
					particionada	- Se divide el espacio muestral de acuerdo al número de clusters a crear y se identifican los centroides
		
		[-e num]
			Establece el número de iteraciones del algoritmo.
			
		[-c num]
			Establece la delta usada para comparar la convergencia del error obtenido en dos iteraciones sucesivas (su variación permitida)
			
		Si no se indican ni e ni c, se consideran los valores por defecto 10 y 0.00001. En caso de que solo se especifique uno de ellos, únicamente ese se tendrá en cuenta como criterio de parada del algoritmo
			
		[-h]
			imprime la ayuda para la ejecución del programa. Si este parámetro se especifica, finaliza el programa tras mostrar la ayuda, independientemente del resto de parámetros especificados
		

Precondiciones:

	Disponer para su ejecución de la máquina virtual JAVA v7.
	El fichero proporcionado tiene extensión y formato CSV y únicamente contiene instancias cuyos atributos son valores numéricos. No se permiten valores desconocidos para los atributos.
	Además, se encuentra bien estructurado, puede contener comentarios (identificados por %, comentario hasta final de línea) y lineas vacías o con espacios en blanco en cualquier sitio del fichero. Es obligatorio que contenga una línea con los nombres de los atributos de las instancias (separados por comas) tras la cual se escecificarán los valores de una instancia por línea


Postcondiciones:
	
	Se ejecuta el algoritmo K-means clustering de acuerdo a los parámetros indicados.
	
	Además, se generan dos ficheros resultado:
		- nomficherooriginal-inf.txt		Contiene la información de los clusters por cada iteración con el resultado de la iteración final.
		- nomficherooriginal-etiquetado.csv	Contiene las instancias en formato CSV en el mismo orden que fueron cargadas. A cada instancia se le añade un valor correspondiente con el índice del cluster al que finalmente ha sido asociada. Además, se indica en la cabecera el comando con los parámetros utilizados para llamar al programa
	
	Los resultados para unos mismos parámetros y datos son muy variantes debido a la inicialización aleatoria efectuada en la mayor parte de los casos.
	Solo en el caso de la inicialización "particionada" se pueden obtener los mismos resultados en varias ejecuciones con los mismos parámetros y datos, ya que los centroides generados son siempre los mismos.
	
	Todo resultado correspondiente a un número real se muestra redondeado a 3 decimales. En el caso de la convergencia, el redondeo es de 5 decimales.
	Los valores en el fichero etiquetado son los exactos recogidos en la carga de las instancias
	
	
	
***************************************MDP6_param_variator.sh****************************************************

	Objetivo:
		Script en BASH que hace variar los 4 tipos de inicializacion y 4 tipos de distancias para obtener los ficheros resultado
	
	Precondiciones:
		Utilizar un sistema con el intérprete BASH
		situar el ejecutable "MDP6.jar" y el fichero "iris.csv" en la misma carpeta que el fichero script.
	
	Postcondiciones:
		Se ejecuta 16 veces el software desarrollado con diferentes parámetros, obteniéndose como resultado el par de ficheros generados por cada ejecución.
		
***************************************MDP6_var_res.jar*******************************************************

	Objetivo:
		Ejecutar muchas veces el algoritmo Kmeans, variando el tipo de inicialización del algoritmo usando la distancia euclidea
		
	Ejecución:
	
		Comando: java -jar MDP6_var_res.jar Fichero_Instancias
		
		Argumentos:
	
		Fichero_Instancias
			Fichero que contiene el conjunto de datos a utlilizar. En formato csv
	
	Precondiciones:

		Disponer para su ejecución de la máquina virtual JAVA v7.
		El fichero proporcionado tiene extensión y formato CSV y únicamente contiene instancias cuyos atributos son valores numéricos. No se permiten valores desconocidos para los atributos.
		Además, se encuentra bien estructurado, puede contener comentarios (identificados por %, comentario hasta final de línea) y lineas vacías o con espacios en blanco en cualquier sitio del fichero. Es obligatorio que contenga una línea con los nombres de los atributos de las instancias (separados por comas) tras la cual se escecificarán los valores de una instancia por línea
			
	Postconndiciones:
	
		Se generan 3 ficheros, cada uno contiene los resultados para una "K" concreta. Las establecidas por defecto son 3 , 5 y 8
		En cada fichero se guarda un tabla con las iteraciones realizadas (10000 por defecto) y los 4 tipos de inicialización del algoritmo. Para cada una de estas combinaciones se almacena el error cuadrático medio resultado de la ejecución del algoritmo.
		
		Además, todos los mensajes de cada ejecución se muestran por consola.

****************************************MDP6_tab_errores.jar***************************************************

	Objetivo:
		Obtener una tabla en la que aparezca para una distancia contreta, variaciones de K y de la inicialización usada. Para cada configuración de K, inicialización y distancia se calculará el Error Cuadrático. Como hay inicializaciones que pueden variar sus resultados por el uso de aleatorios, cada una de estas combinaciones se ejecuta 100 veces y se obtiene la media de esos 100 Errores Cuadráticos, medida más estable que la de una sola ejecución del algoritmo.
		
		Esta tabla será comparable a la medida del error ofrecida por Weka solo en el caso de la distancia Euclídea.
	
	Ejecución:
	
		Comando: java -jar MDP6_tab_errores.jar Fichero_Instancias [-d (M m|C)]
		
	Argumentos:
	
		Fichero_Instancias
			Fichero que contiene el conjunto de datos a utlilizar. En formato csv
			
		[-d (M m|C)]
			Establece la métrica a utilizar en el desarrollo del algoritmo. Si no se especifica se usa por defecto la Euclidea
				
				Posibilidades:
					M m	- Distancia Minkowski con la especificación del parámetro m
					C	- Distancia Chebyshev
		
	Precondiciones:
		
		Disponer para su ejecución de la máquina virtual JAVA v7.
		El fichero proporcionado tiene extensión y formato CSV y únicamente contiene instancias cuyos atributos son valores numéricos. No se permiten valores desconocidos para los atributos.
		Además, se encuentra bien estructurado, puede contener comentarios (identificados por %, comentario hasta final de línea) y lineas vacías o con espacios en blanco en cualquier sitio del fichero. Es obligatorio que contenga una línea con los nombres de los atributos de las instancias (separados por comas) tras la cual se escecificarán los valores de una instancia por línea
		
	Postcondiciones:
	
		Se genera un fichero delmismo nombre que el fichero fuente con el sufijo "tabla-errores-distanciaUsada.txt"	
		El fichero contiene una tabla en la que por cada combinación de k (2,3,5,7 y 9), cada una de las 4 inicializaciones disponibles y la distancia especificada, se calcula la media por cada combinación del algoritmo de 100 Errores Cuadráticos extraídos de una misma combinación de parámetros.
		