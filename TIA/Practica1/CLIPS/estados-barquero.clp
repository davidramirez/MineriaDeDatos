(defglobal 
?*ESTADO-INICIAL* = (create$ i i i i)
?*LISTA* = (create$   (implode$ ?*ESTADO-INICIAL*))
 ?*PADRE* = ?*ESTADO-INICIAL*
 ?*OPERADORES* = (create$ MoverP MoverL MoverC MoverB)
 ?*SECUENCIA-DE-OPERADORES* = (create$)
 ?*VISITADOS* = (create$)
 ?*PASOS* = 10
)

;sistema-aspiradora: lista (h1 aspiradora sucia h2 sucia)


(deffunction igualBarquero? ($?pos, $?estado)

(return(eq (nth$ ?pos ?estado) (nth$ 4 ?estado)))
)

(deffunction exito ($?estado)

(return (not(member$ (create$ i) ?estado))))

(deffunction fracaso ($?estado)
--comprobar que el estado es posible. si no lo es devover PROHIBIDO
)

(deffunction contrario (?pos)
  (if (eq ?pos d) then
    (return i)

  else
    (return d)
  )
)

(deffunction A ($?estado)
  (bind ?h1 (aspirar(extrae-h1 ?estado)))
  (bind ?h2 (aspirar(extrae-h2 ?estado)))
  (create$ ?h1 ?h2))

(deffunction MoverP ($?estado)

(if (igualBarquero? 1 ?estado) then
  (create$ (contrario (nth$ 1 ?estado)) (nth$ 2 ?estado) (nth$ 3 ?estado) (contrario (nth$ 4 ?estado)))
)

(return (fracaso ?estado))
)

(deffunction MoverC ($?estado)
(if (igualBarquero? 2 ?estado) then
  (create$ (nth$ 1 ?estado) (contrario (nth$ 2 ?estado)) (nth$ 3 ?estado) (contrario (nth$ 4 ?estado)))
)

(return (fracaso ?estado))
)

(deffunction MoverL ($?estado)
(if (igualBarquero? 3 ?estado) then
  (create$ (nth$ 1 ?estado) (nth$ 2 ?estado) (contrario (nth$ 3 ?estado)) (contrario (nth$ 4 ?estado)))
)

(return (fracaso ?estado))
)

(deffunction MoverB ($?estado)
  (create$ (nth$ 1 ?estado) (nth$ 2 ?estado) (nth$ 3 ?estado) (contrario (nth$ 4 ?estado))

(return (fracaso ?estado))
)