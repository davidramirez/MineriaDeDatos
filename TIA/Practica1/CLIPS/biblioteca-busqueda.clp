(deffunction prohibido? ($?estado)
    ;cambio la comparación para que sea entre valores multicampo y no multicampo-string que no va
 (eq ?estado (create$ PROHIBIDO))
)


(deffunction aplicar-operador (?operador $?estado)
(eval
(format nil "( %s (create$ %s))" ?operador (implode$ ?estado))
)
)


(deffunction operadores-hijos($?estado)
(bind $?lista-operadores (create$))
(progn$ (?op ?*OPERADORES*)
        (bind $?hijo (aplicar-operador ?op ?estado))
        (if (not (prohibido? ?hijo)) then
                (bind ?lista-operadores (create$ ?lista-operadores ?op))))
?lista-operadores)

(deffunction hijos($?estado)
(bind $?lista-hijos (create$))
(progn$ (?op ?*OPERADORES*)
        (bind $?hijo (aplicar-operador ?op ?estado))
        (if (not (prohibido? ?hijo)) then
                (bind ?lista-hijos (create$ ?lista-hijos (implode$  ?hijo))))
)
;Añado el return de los hijos, sino devuelve false
(return ?lista-hijos))



(deffunction busqueda-en-profundidad ($?lista)
(bind ?i 0)
(while (and(not (exito ?*PADRE*)) (not (eq ?*LISTA* (create$)))) do
        (printout t "Paso " ?i crlf)
        (bind ?*PADRE*  (explode$ (nth$ 1  ?*LISTA*)))
        (printout t "Padre " ?*PADRE* crlf)
        (bind ?*LISTA* (rest$ ?*LISTA*))
        (if (not (exito ?*PADRE*)) then
                (bind ?operadores-hijos (operadores-hijos ?*PADRE*))
                (bind ?hijos (hijos ?*PADRE*))
                (bind ?*LISTA* (create$ (hijos ?*PADRE*)   ?*LISTA*)))
        (bind ?i (+ ?i 1))
        (if (and (> ?*PASOS* 0) (= ?i ?*PASOS*)) then (break))
)

(if  (exito ?*PADRE*) then (printout t "La solución es " ?*PADRE* crlf)
else (if (=(length$ ?*LISTA*)0)  then (printout t "No hay solución" crlf)))
)


(deffunction busqueda-en-profundidad-con-visitados ($?lista)
(bind ?i 0)
(while (and(not (exito ?*PADRE*)) (not (eq ?*LISTA* (create$)))) do
        (printout t "Paso " ?i crlf)
        (bind ?*PADRE*  (explode$ (nth$ 1  ?*LISTA*)))
        (printout t "Padre " ?*PADRE* crlf)
        (bind ?*LISTA* (rest$ ?*LISTA*))
        (if (not(member$ (implode$ ?*PADRE*) ?*VISITADOS*)) then
            (bind ?*VISITADOS* (create$ ?*VISITADOS* (implode$ ?*PADRE*)))
            (if (not (exito ?*PADRE*)) then
                (bind ?operadores-hijos (operadores-hijos ?*PADRE*))
                (bind ?hijos (hijos ?*PADRE*))
                (bind ?*LISTA* (create$ (hijos ?*PADRE*)   ?*LISTA*))
                (bind ?i (+ ?i 1))
             )
        )
        (if (and (> ?*PASOS* 0) (= ?i ?*PASOS*)) then (break))
)

(if  (exito ?*PADRE*) then (printout t "La solución es " ?*PADRE* crlf)
else (if (=(length$ ?*LISTA*)0)  then (printout t "No hay solución" crlf)))
)


(deffunction busqueda-en-anchura($?lista)
(bind ?i 0)
(while (and(not (exito ?*PADRE*)) (not (eq ?*LISTA* (create$)))) do
        (printout t "Paso " ?i crlf)
        (bind ?*PADRE*  (explode$ (nth$ 1  ?*LISTA*)))
        (printout t "Padre " ?*PADRE* crlf)
        (bind ?*LISTA* (rest$ ?*LISTA*))
        (if (not (exito ?*PADRE*)) then
                (bind ?operadores-hijos (operadores-hijos ?*PADRE*))
                (bind ?hijos (hijos ?*PADRE*))
                (bind ?*LISTA* (create$ ?*LISTA*  (hijos ?*PADRE*) )))
        (bind ?i (+ ?i 1))
        (if (and (> ?*PASOS* 0) (= ?i ?*PASOS*)) then (break))
)

(if  (exito ?*PADRE*) then (printout t "La solución es " ?*PADRE* crlf)
else (if (=(length$ ?*LISTA*)0)  then (printout t "No hay solución" crlf)))
)


(deffunction busqueda-en-anchura-con-visitados ($?lista)
(bind ?i 0)
(while (and(not (exito ?*PADRE*)) (not (eq ?*LISTA* (create$)))) do
        (printout t "Paso " ?i crlf)
        (bind ?*PADRE*  (explode$ (nth$ 1  ?*LISTA*)))
        (printout t "Padre " ?*PADRE* crlf)
        (bind ?*LISTA* (rest$ ?*LISTA*))
        (if (not(member$ (implode$ ?*PADRE*) ?*VISITADOS*)) then
            (bind ?*VISITADOS* (create$ ?*VISITADOS* (implode$ ?*PADRE*)))
            ;(printout t "visitados " ?*VISITADOS* crlf); Imprimo los visitados para comprobar que se guarda cada estado de forma individual
            (if (not (exito ?*PADRE*)) then
                (bind ?operadores-hijos (operadores-hijos ?*PADRE*))
                (bind ?hijos (hijos ?*PADRE*))
                (bind ?*LISTA* (create$  ?*LISTA* (hijos ?*PADRE*)))
                (bind ?i (+ ?i 1))
             )
        )
        (if (and (> ?*PASOS* 0) (= ?i ?*PASOS*)) then (break))
)

(if  (exito ?*PADRE*) then (printout t "La solución es " ?*PADRE* crlf)
else (if (=(length$ ?*LISTA*)0)  then (printout t "No hay solución" crlf)))
)

(deffunction busqueda-exhaustiva (?algoritmo ?visitados $?lista)
	(if (eq ?algoritmo p)
	then
		(if (eq ?visitados TRUE) 
		then
			(busqueda-en-profundidad-con-visitados ?lista)
		else
			(busqueda-en-profundidad ?lista)
		)
	else
		(if (eq ?visitados TRUE)
		then
			(busqueda-en-anchura-con-visitados ?lista)
		else
			(busqueda-en-anchura ?lista)
		)
	)
)