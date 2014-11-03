(defglobal 
?*ESTADO-INICIAL* = (create$ i i i i)
?*LISTA* = (create$   (implode$ ?*ESTADO-INICIAL*))
 ?*PADRE* = ?*ESTADO-INICIAL*
 ?*OPERADORES* = (create$ MoverP MoverL MoverC MoverB)
 ?*SECUENCIA-DE-OPERADORES* = (create$)
 ?*VISITADOS* = (create$)
 ?*PASOS* = 10
)

; estado: lista (p c l b), donde p,b,l,c €(i,d)


(deffunction igualBarquero? (?pos $?estado)

(return(eq (nth$ ?pos ?estado) (nth$ 4 ?estado)))
)

(deffunction exito ($?estado)

(return (not(member$ (create$ i) ?estado))))

(deffunction fracaso ($?estado)
;comprobar que el estado es posible. si no lo es devover PROHIBIDO
;cabra y puma solos y lechuga y cabra solos

(if(and(and(eq (nth$ 1 ?estado) (nth$ 2 ?estado)) (not(eq (nth$ 1 ?estado) (nth$ 4 ?estado)))) (not (eq (nth$ 2 ?estado) (nth$ 4 ?estado)))) then
(return (create$ PROHIBIDO))
else
  (if (and(and(eq (nth$ 3 ?estado) (nth$ 2 ?estado)) (not(eq (nth$ 2 ?estado) (nth$ 4 ?estado)))) (not (eq (nth$ 3 ?estado) (nth$ 4 ?estado)))) then
    (return (create$ PROHIBIDO))
  else
    (return ?estado)
  )

)
)

(deffunction contrario (?pos)
  (if (eq ?pos d) then
    (return i)
  else
    (return d)
  )
)


(deffunction MoverP ($?estado)

(if (igualBarquero? 1 ?estado) then
  (return (fracaso (create$ (contrario (nth$ 1 ?estado)) (nth$ 2 ?estado) (nth$ 3 ?estado) (contrario (nth$ 4 ?estado)))))
)

(return (fracaso ?estado))
)

(deffunction MoverC ($?estado)
(if (igualBarquero? 2 ?estado) then
  (return (fracaso (create$ (nth$ 1 ?estado) (contrario (nth$ 2 ?estado)) (nth$ 3 ?estado) (contrario (nth$ 4 ?estado)))))
)

(return (fracaso ?estado))
)

(deffunction MoverL ($?estado)
(if (igualBarquero? 3 ?estado) then
  (return (fracaso (create$ (nth$ 1 ?estado) (nth$ 2 ?estado) (contrario (nth$ 3 ?estado)) (contrario (nth$ 4 ?estado)))))
else
  (return ?estado))


)

(deffunction MoverB ($?estado)
  
(return (fracaso (create$ (nth$ 1 ?estado) (nth$ 2 ?estado) (nth$ 3 ?estado) (contrario (nth$ 4 ?estado)))))
)