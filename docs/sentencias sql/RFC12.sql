SELECT *
FROM  ALOJAMIENTOS a , RESERVAS r , SERVICIOS s
WHERE r.idalojamiento = a.id
AND  a.id= s.idalojamiento
AND r.diareserva BETWEEN TO_DATE ('01/02/20')AND TO_DATE ('01/10/20')
AND s.nombre ='Jacuzzi'
AND s.tomaservicio = 'Y' ;