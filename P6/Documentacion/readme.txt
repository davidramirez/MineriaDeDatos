

	java -jar MDP6.jar Fichero_Instancias -k num [-d (M m)|(C)] [-i (aleatoria|pert_aleatoria|cent_aleat|particonada)] [-e num] [-c num] [-h]
	
	
	-e num
		Establece el número de iteraciones
		
	-c num
		Establece la delta usada para comparar la convergaencia de dos grupos de centroides (su variación)
		
		Si no se indican ni e ni c, se consideran los valores por defecto 10 y 0.00001
		
	-h
		imprime la ayuda
		
	Salida:
	
	Los resultados se imprimen en un fichero nomfichero-rdo.txt