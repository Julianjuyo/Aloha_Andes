/*
RFC11 - CONSULTAR CONSUMO EN ALOHANDES – RFC10-V2

Se quiere conocer la información de los usuarios QUE NO realizaron al menos una reserva de una determinada oferta de alojamiento en un rango de fechas. 
En la clasificación debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta como, por ejemplo, 
por los datos del cliente, por oferta de alojamiento y por tipo de alojamiento.

NOTA: Respetando la privacidad de los clientes, cuando un cliente proveedor hace esta consulta obtiene la información de su propia actividad, 
mientras que el administrador obtiene toda la información de cualquiera de los clientes. Ver RNF1.

*/


SELECT count(*) Numreservas, mi.id
FROM MIEM_CO_UNIV mi  JOIN RESERVAS r ON mi.id = r.idmiembro
WHERE  r.diareserva NOT BETWEEN '03-02-20' AND  '15-02-20'
AND r.idalojamiento =1
GROUP BY mi.id
ORDER BY mi.id;

SELECT count(*) Numreservas, r.numreserva
FROM MIEM_CO_UNIV mi  JOIN RESERVAS r ON mi.id = r.idmiembro
WHERE  r.diareserva NOT BETWEEN '03-02-20' AND  '15-02-20'
AND r.idalojamiento =1
GROUP BY r.numreserva
ORDER BY r.numreserva;

SELECT count(*) Numreservas, mi.id
FROM MIEM_CO_UNIV mi  JOIN RESERVAS r ON mi.id = r.idmiembro
WHERE  r.diareserva NOT BETWEEN '03-02-20' AND  '15-02-20'
GROUP BY mi.id
ORDER BY mi.id;



