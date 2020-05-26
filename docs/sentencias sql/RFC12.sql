--RFC12 - CONSULTAR FUNCIONAMIENTO--

/*Muestra, para cada semana del a�o, la oferta de alojamiento con m�s ocupaci�n, la oferta de alojamiento con
menos ocupaci�n, los operadores m�s solicitados y los operadores menos solicitados. Las respuestas deben
ser sustentadas por el detalle de las ofertas de alojamiento y operadores correspondientes. Esta operaci�n es
realizada el gerente general de AlohAndes*/

--A�o 2019--
--Oferta con m�s ocupaci�n--

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

--Operadores m�s solicitados--

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