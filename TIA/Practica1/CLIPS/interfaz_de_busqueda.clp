(defglobal 
?*CAMINO* = "\\home\\user\\"
?*FICHERO* = "estados.clp"
?*BIBLIOTECA* = "biblioteca-busqueda.clp"
?*ALGORITMOS* = 
" 
1.Búsqueda-en-profundidad-sin-visitados
2.Búsqueda-en-profundidad-con-visitados
3.Búsqueda-en-anchura-sin-visitados
4.Búsqueda-en-anchura-con-visitados
"

)

(deffunction ejecutar-busqueda()
(load (str-cat ?*CAMINO* ?*FICHERO*))
(load (str-cat ?*CAMINO* ?*BIBLIOTECA*))
(printout t "Cuántos pasos quiere ejecutar? si quiere todos escribir 0 y si el mismo -1" crlf)
(bind ?pasos (read))
(if (> ?pasos -1) then(eval (str-cat "(bind ?*PASOS* " ?pasos ")")))
(printout t "¿Qué algoritmo quiere aplicar?" crlf ?*ALGORITMOS* crlf)
(bind ?algoritmo (read))
(switch ?algoritmo
	(case 1 then (eval   "(busqueda-exhaustiva p FALSE ?*LISTA*)"))
	(case 2 then (eval   "(busqueda-exhaustiva p TRUE ?*LISTA*)"))
	(case 3 then (eval   "(busqueda-exhaustiva a FALSE ?*LISTA*)"))
	(case 4 then (eval   "(busqueda-exhaustiva a TRUE ?*LISTA*)"))
	(default (printout t "No implementado" crlf )
))
)










