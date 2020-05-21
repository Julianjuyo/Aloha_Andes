--RFC6- MOSTRAR EL USO DE ALOHANDES PARA UN USUARIO DADO(NÚMERO DE NOCHES O MESES CONTRATADOS, CARACTERÍSTICAS DEL ALOJAMIENTO UTILIZADO, DINERO PAGADO.

SELECT mi.id, mi.tipoid, r.tiempodias, a.id, r.numreserva , s.nombre, s.descripcion
FROM RESERVAS r , SERVICIOS s, MIEM_CO_UNIV mi, ALOJAMIENTOS a
WHERE mi.id = r.idmiembro
AND r.numreserva = a.id
AND a.id = s.idalojamiento
AND mi.id = 2016444;









