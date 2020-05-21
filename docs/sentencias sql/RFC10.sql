/*
CONSULTAR CONSUMO EN ALOHANDES

Se quiere conocer la información de los usuarios que realizaron al menos una reserva de una determinada oferta de alojamiento en un rango de fechas. 
Los resultados deben ser clasificados según un criterio deseado por quien realiza la consulta. 
En la clasificación debe ofrecerse la posibilidad de agrupamiento 
y ordenamiento de las respuestas según los intereses del usuario que consulta como, 
por ejemplo, por los datos del cliente, por oferta de alojamiento y por tipo de alojamiento.

NOTA: Respetando la privacidad de los clientes, cuando un cliente proveedor hace esta consulta obtiene la información de su propia actividad, 
mientras que el administrador obtiene toda la información de cualquiera de los clientes. Ver RNF1.

*/


DEFINE START_DATE = "to_date('03/01/18', 'dd/mm/yy')"
DEFINE END_DATE = "to_date('01/06/18', 'dd/mm/yy')"

SELECT mi.id
FROM MIEM_CO_UNIV mi , RESERVAS r, ALOJAMIENTOS a
WHERE mi.id= r.idmiembro
AND r.idalojamiento = a.id
AND a.id = 1
AND r.diareserva BETWEEN '01-01-19' AND  '01-11-20' 
GROUP BY mi.id
ORDER BY mi.id ;

SELECT r.numreserva
FROM MIEM_CO_UNIV mi , RESERVAS r, ALOJAMIENTOS a
WHERE mi.id= r.idmiembro
AND r.idalojamiento = a.id
AND a.id = 1
AND r.diareserva BETWEEN '01-01-19' AND  '01-11-20' 
GROUP BY r.numreserva
ORDER BY r.numreserva;


