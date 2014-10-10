

	java -jar MDP6.jar Fichero_Instancias [-d (M m)|(C)] [-i (aleatoria|pert_aleatoria|particonada)] [-e num] [-c num]
	
	
	-e num
		Establece el número de iteraciones
		
	-c num
		Establece la delta usada para comparar la convergaencia de dos grupos de centroides (su variación)
		
		Si no se indican ni e ni c, se consideran los valores por defecto 10 y 0.00001
		
	Salida:
	
	Los resultados se imprimen en un fichero nomfichero-rdo.txt