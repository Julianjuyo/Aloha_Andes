/*
RFC3- MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS
*/
                           
                                   

SELECT t1 OfertaAlojamiento, NVL(((totales/reservados)*100), 0) IndiceOcupacion
FROM (SELECT op.tipoOperador t1, count(*) totales
      FROM ALOJAMIENTOS a, OPERADORES op
            WHERE a.idoperador = op.id
      GROUP BY op.tipoOperador) LEFT JOIN (SELECT op.tipoOperador t2, count(*) reservados
                                           FROM ALOJAMIENTOS a, OPERADORES op, RESERVAS r
                                                WHERE a.idoperador = op.id 
                                                AND r.idalojamiento = a.id
                                           GROUP BY op.tipoOperador)
ON t1 = t2;  







 