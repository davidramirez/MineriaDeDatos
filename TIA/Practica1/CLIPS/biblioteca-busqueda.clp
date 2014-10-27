(deffunction prohibido? ($?estado)
(eq $?estado PROHIBIDO)
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
))



(deffunction busqueda-en-profundidad ($?lista)
(bind ?i 0)
(while (and(not (exito ?*PADRE*)) (not (eq ?*LISTA* (create$)))) do
        (printout t "Paso " ?i crlf)
        (bind ?*PADRE*  (explode$(nth$ 1  ?*LISTA*)))
        (printout t "Padre " ?*PADRE* crlf)
        (bind ?*LISTA*(rest$ ?*LISTA*))
        (if (not (exito ?*PADRE*)) then
                (bind ?operadores-hijos (operadores-hijos ?*PADRE*))
                (bind ?hijos (hijos ?*PADRE*))
                (bind ?*LISTA* (create$ (hijos ?*PADRE*)   ?*LISTA*)))
        (bind ?i (+ ?i 1))
        (if (and (> ?*PASOS* 0) (= ?i ?*PASOS*)) then (break))
)

(if  (exito ?*PADRE*) then (printout t "La soluci�n es " ?*PADRE* crlf)
else (if (=(length$ ?*LISTA*)0)  then (printout t "No hay soluci�n" crlf)))
)


(deffunction busqueda-en-profundidad-con-visitados ($?lista)
(bind ?i 0)
(while (and(not (exito ?*PADRE*)) (not (eq ?*LISTA* (create$)))) do
        (printout t "Paso " ?i crlf)
        (bind ?*PADRE*  (explode$(nth$ 1  ?*LISTA*)))
        (printout t "Padre " ?*PADRE* crlf)
        (bind ?*LISTA*(rest$ ?*LISTA*))
        (if (not(member$ (nth$ 1  ?*LISTA*) ?*VISITADOS*)) then
            (bind ?*VISITADOS* (create$ ?*VISITADOS* (nth$ 1  ?*LISTA*)))
            (if (not (exito ?*PADRE*)) then
                (bind ?operadores-hijos (operadores-hijos ?*PADRE*))
                (bind ?hijos (hijos ?*PADRE*))
                (bind ?*LISTA* (create$ (hijos ?*PADRE*)   ?*LISTA*))
                (bind ?i (+ ?i 1))
             )
        )
        (if (and (> ?*PASOS* 0) (= ?i ?*PASOS*)) then (break))
)

(if  (exito ?*PADRE*) then (printout t "La soluci�n es " ?*PADRE* crlf)
else (if (=(length$ ?*LISTA*)0)  then (printout t "No hay soluci�n" crlf)))
)


(deffunction busqueda-en-anchura($?lista)
(bind ?i 0)
(while (and(not (exito ?*PADRE*)) (not (eq ?*LISTA* (create$)))) do
        (printout t "Paso " ?i crlf)
        (bind ?*PADRE*  (explode$(nth$ 1  ?*LISTA*)))
        (printout t "Padre " ?*PADRE* crlf)
        (bind ?*LISTA*(rest$ ?*LISTA*))
        (if (not (exito ?*PADRE*)) then
                (bind ?operadores-hijos (operadores-hijos ?*PADRE*))
                (bind ?hijos (hijos ?*PADRE*))
                (bind ?*LISTA* (create$ ?*LISTA*  (hijos ?*PADRE*) )))
        (bind ?i (+ ?i 1))
        (if (and (> ?*PASOS* 0) (= ?i ?*PASOS*)) then (break))
)

(if  (exito ?*PADRE*) then (printout t "La soluci�n es " ?*PADRE* crlf)
else (if (=(length$ ?*LISTA*)0)  then (printout t "No hay soluci�n" crlf)))
)


(deffunction busqueda-en-anchura-con-visitados ($?lista)
(bind ?i 0)
(while (and(not (exito ?*PADRE*)) (not (eq ?*LISTA* (create$)))) do
        (printout t "Paso " ?i crlf)
        (bind ?*PADRE*  (explode$(nth$ 1  ?*LISTA*)))
        (printout t "Padre " ?*PADRE* crlf)
        (bind ?*LISTA*(rest$ ?*LISTA*))
        (if (not(member$ (nth$ 1  ?*LISTA*) ?*VISITADOS*)) then
            (bind ?*VISITADOS* (create$ ?*VISITADOS* (nth$ 1  ?*LISTA*)))
            (if (not (exito ?*PADRE*)) then
                (bind ?operadores-hijos (operadores-hijos ?*PADRE*))
                (bind ?hijos (hijos ?*PADRE*))
                (bind ?*LISTA* (create$  ?*LISTA* (hijos ?*PADRE*)))
                (bind ?i (+ ?i 1))
             )
        )
        (if (and (> ?*PASOS* 0) (= ?i ?*PASOS*)) then (break))
)

(if  (exito ?*PADRE*) then (printout t "La soluci�n es " ?*PADRE* crlf)
else (if (=(length$ ?*LISTA*)0)  then (printout t "No hay soluci�n" crlf)))
)