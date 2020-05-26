--RFC12 - CONSULTAR FUNCIONAMIENTO--

/*Muestra, para cada semana del año, la oferta de alojamiento con más ocupación, la oferta de alojamiento con
menos ocupación, los operadores más solicitados y los operadores menos solicitados. Las respuestas deben
ser sustentadas por el detalle de las ofertas de alojamiento y operadores correspondientes. Esta operación es
realizada el gerente general de AlohAndes*/

--Año 2019--
--Oferta con más ocupación--

SELECT Semana, TipoAlojamiento, FIRST_VALUE(cantidadOcupados) OVER (PARTITION BY Semana ORDER BY cantidadOcupados DESC) cantidadOcupados
FROM (SELECT to_char(r.diareserva, 'WW') Semana, o.tipooperador TipoAlojamiento, count(r.numreserva) cantidadOcupados
        FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
        WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND EXTRACT(YEAR FROM r.diareserva) = 2019
        GROUP BY to_char(r.diareserva, 'WW'), o.tipooperador)
INTERSECT
SELECT to_char(r.diareserva, 'WW') Semana, o.tipooperador TipoAlojamiento, count(r.numreserva) cantidadOcupados
FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND EXTRACT(YEAR FROM r.diareserva) = 2019
GROUP BY to_char(r.diareserva, 'WW'), o.tipooperador
ORDER BY Semana ASC;

--Oferta con menos ocupacion--

SELECT Semana, TipoAlojamiento, FIRST_VALUE(cantidadOcupados) OVER (PARTITION BY Semana ORDER BY cantidadOcupados ASC) cantidadOcupados
FROM (SELECT to_char(r.diareserva, 'WW') Semana, o.tipooperador TipoAlojamiento, count(r.numreserva) cantidadOcupados
        FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
        WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND EXTRACT(YEAR FROM r.diareserva) = 2019
        GROUP BY to_char(r.diareserva, 'WW'), o.tipooperador)
INTERSECT
SELECT to_char(r.diareserva, 'WW') Semana, o.tipooperador TipoAlojamiento, count(r.numreserva) cantidadOcupados
FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND EXTRACT(YEAR FROM r.diareserva) = 2019
GROUP BY to_char(r.diareserva, 'WW'), o.tipooperador
ORDER BY Semana ASC;

--Operadores más solicitados--

SELECT Semana, Operador, FIRST_VALUE(cantidadOcupados) OVER (PARTITION BY Semana ORDER BY cantidadOcupados DESC) cantidadOcupados
FROM (SELECT to_char(r.diareserva, 'WW') Semana, o.nombre Operador, count(r.numreserva) cantidadOcupados
        FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
        WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND EXTRACT(YEAR FROM r.diareserva) = 2019
        GROUP BY to_char(r.diareserva, 'WW'), o.nombre)
INTERSECT
SELECT to_char(r.diareserva, 'WW') Semana, o.nombre Operador, count(r.numreserva) cantidadOcupados
FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND EXTRACT(YEAR FROM r.diareserva) = 2019
GROUP BY to_char(r.diareserva, 'WW'), o.nombre
ORDER BY Semana ASC;

--Operadores menos solicitados--

SELECT Semana, Operador, FIRST_VALUE(cantidadOcupados) OVER (PARTITION BY Semana ORDER BY cantidadOcupados ASC) cantidadOcupados
FROM (SELECT to_char(r.diareserva, 'WW') Semana, o.nombre Operador, count(r.numreserva) cantidadOcupados
        FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
        WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND EXTRACT(YEAR FROM r.diareserva) = 2019
        GROUP BY to_char(r.diareserva, 'WW'), o.nombre)
INTERSECT
SELECT to_char(r.diareserva, 'WW') Semana, o.nombre Operador, count(r.numreserva) cantidadOcupados
FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND EXTRACT(YEAR FROM r.diareserva) = 2019
GROUP BY to_char(r.diareserva, 'WW'), o.nombre
ORDER BY Semana ASC;