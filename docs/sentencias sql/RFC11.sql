/*
RFC11 - CONSULTAR CONSUMO EN ALOHANDES – RFC10-V2

Se quiere conocer la información de los usuarios QUE NO realizaron al menos una reserva de una determinada oferta de alojamiento en un rango de fechas. 
En la clasificación debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario que consulta como, por ejemplo, 
por los datos del cliente, por oferta de alojamiento y por tipo de alojamiento.

NOTA: Respetando la privacidad de los clientes, cuando un cliente proveedor hace esta consulta obtiene la información de su propia actividad, 
mientras que el administrador obtiene toda la información de cualquiera de los clientes. Ver RNF1.

*/

SELECT *
FROM MIEM_CO_UNIV miem 
minus
(SELECT  mi.*
 FROM MIEM_CO_UNIV mi , RESERVAS r, ALOJAMIENTOS a
 WHERE mi.id= r.idmiembro
 AND r.idalojamiento = a.id
 AND a.id = 599
 AND r.diareserva  BETWEEN '01-01-18' AND  '12-05-26');














