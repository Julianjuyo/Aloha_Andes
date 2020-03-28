SELECT *
FROM  ALOJAMIENTOS a , RESERVAS r , SERVICIOS s
WHERE r.idalojamiento = a.id
AND  a.id= s.idalojamiento
AND r.diareserva BETWEEN TO_DATE ('2020/02/01', 'yyyy/mm/dd')AND TO_DATE ('2020/02/28', 'yyyy/mm/dd')
AND s.nombre ='INTERNET'
AND s.tomaservicio = 'Y' ;