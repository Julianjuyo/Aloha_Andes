SELECT a.id
FROM  ALOJAMIENTOS a , RESERVAS r , SERVICIOS s
WHERE r.idalojamiento = a.id
AND  a.id= s.idalojamiento
AND r.diareserva BETWEEN TO_DATE ('31/01/20')AND TO_DATE ('31/10/20')
AND s.nombre ='Jacuzzi'
AND s.tomaservicio = 'Y' ;