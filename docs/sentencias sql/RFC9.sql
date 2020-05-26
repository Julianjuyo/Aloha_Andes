/*
RFC9- ENCONTRAR LAS OFERTAS DE ALOJAMIENTO QUE NO TIENEN MUCHA DEMANDA

Encontrar las ofertas de alojamiento que no han recibido clientes en periodos superiores a 1 mes, durante todo el periodo de operación de AlohAndes.
*/
	  
	SELECT id  
	FROM (SELECT a.id, sum(r.tiempodias) cantidad  
	        FROM RESERVAS r, ALOJAMIENTOS a  
	        WHERE r.idalojamiento = a.id  
	        group by a.id)  
	WHERE cantidad < 30;  
