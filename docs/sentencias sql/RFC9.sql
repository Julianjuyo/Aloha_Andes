/*
RFC9- ENCONTRAR LAS OFERTAS DE ALOJAMIENTO QUE NO TIENEN MUCHA DEMANDA

Encontrar las ofertas de alojamiento que no han recibido clientes en periodos superiores a 1 mes, durante todo el periodo de operación de AlohAndes.
*/

SELECT *
FROM ALOJAMIENTOS a, RESERVAS r
WHERE a.id = r.idalojamiento
AND r.tiempodias <30;