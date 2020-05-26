--RFC6- MOSTRAR EL USO DE ALOHANDES PARA UN USUARIO DADO(NÚMERO DE NOCHES O MESES CONTRATADOS, CARACTERÍSTICAS DEL ALOJAMIENTO UTILIZADO, DINERO PAGADO.

SELECT mi.id, mi.tipoid, SUM(r.tiempodias) AS numeroNoches,  SUM(a.precio) AS DineroPagado, op.tipooperador
FROM RESERVAS r ,  MIEM_CO_UNIV mi, ALOJAMIENTOS a, OPERADORES op
WHERE mi.id = r.idmiembro
AND r.numreserva = a.id
AND op.id = a.idoperador
AND mi.id = 100319
GROUP BY mi.id , mi.tipoid, op.tipooperador ;



-- ids 100319 para hotel 
-- ids 94926 para hostal













