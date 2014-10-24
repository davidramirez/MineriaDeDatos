#!/bin/bash

for inicializacion in aleatoria pert_aleatoria cent_aleat particionada
do
	for distancia in 'C' 'M 1' 'M 2' 'M 7.5'
	do
		java -jar ./MDP6.jar ./iris.csv -k 3 -i $inicializacion -d $distancia -e 1000 -c 0.01
	done

done

