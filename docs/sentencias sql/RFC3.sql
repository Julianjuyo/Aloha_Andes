SELECT t1 OfertaAlojamiento, NVL(((totales/reservados)*100), 0) IndiceOcupacion
FROM (SELECT h.tipoophab t1, count(h.idalojamiento) totales
    FROM ALOJAMIENTOS a, HABITACIONES h
    WHERE a.id = h.idalojamiento
    GROUP BY h.tipoophab) LEFT JOIN (SELECT h.tipoophab t2, count(h.idalojamiento) reservados
                                        FROM ALOJAMIENTOS a, HABITACIONES h, RESERVAS r
                                        WHERE a.id = h.idalojamiento AND r.idalojamiento = h.idalojamiento
                                        GROUP BY h.tipoophab)
ON t1 = t2;                                    

SELECT COUNT(*)
FROM APARTAMENTOS;

SELECT COUNT(*)
FROM APARTAMENTOS a LEFT JOIN RESERVAS r
ON a.idalojamiento = r.idalojamiento;

SELECT COUNT(*)
FROM VIVIENDACOMUNIDAD;

SELECT COUNT(*)
FROM VIVIENDACOMUNIDAD v LEFT JOIN RESERVAS r
ON v.idalojamiento = r.idalojamiento;