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
					particionada	- Se divide el espacio muestrald de acuerdo al número de clusters a crear y se identifican los centroides
		
		[-e num]
			Establece el número de iteraciones del algoritmo.
			
		[-c num]
			Establece la delta usada para comparar la convergencia del error obtenido en dos iteraciones sucesivas (su variación permitida)
			
		Si no se indican ni e ni c, se consideran los valores por defecto 10 y 0.00001. En caso de que solo se especifique uno de ellos, únicamente ese se tendrá en cuenta como criterio de parada del algoritmo
			
		[-h]
			imprime la ayuda para la ejecución del programa. Si este parámetro se especifica, finaliza el programa tras mostrar la ayuda, independientemente del resto de parámetros especificados
		

Precondiciones:

	El fichero proporcionado tiene extensión y formato CSV y únicamente contiene instancias cuyos atributos son valores numéricos. No se permiten valores desconocidos para los atributos.
	Además, se encuentra bien estructurado, puede contener comentarios (identificados por %, comentario hasta final de línea) y lineas vacías o con espacios en blanco en cualquier sitio del fichero. Es obligatorio que contenga una línea con los nombres de los atributos de las instancias (separados por comas) tras la cual se escecificarán los valores de una instancia por línea


Postcondiciones:
	
	Se ejecuta el algoritmo K-means clustering de acuerdo a los parámetros indicados.
	
	Además, se generan dos ficheros resultado:
		- nomficherooriginal-inf.txt		Contiene la información de los clusters por cada iteración con el resultado de la iteración final.
		- nomficherooriginal-etiquetado.csv	Contiene las instancias en formato CSV en el mismo orden que fueron cargadas. A cada instancia se le añade un valor correspondiente con el cluster al que finalmente ha sido asociada. Además, se indica en la cabecera el comando con los parámetros utilizados para llamar al programa
	
	Los resultados para unos mismos parámetros y datos son muy variantes debido a la inicialización aleatoria efectuada en la mayor parte de los casos.
	Solo en el caso de la inicialización "particionada" se pueden obtener los mismos resultados en varias ejecuciones con los mismos parámetros y datos, ya que los centroides generados son siempre los mismos.
	
	Todo resultado correspondiente a un número real se muestra redondeado a 3 decimales. En el caso de la convergencia, el redondeo es de 5 decimales.
	Los valores en el fichero etiquetado son los exactos recogidos en la carga de las instancias
Ejemplos: