--RFC13 - CONSULTAR LOS BUENOS CLIENTES--

/*Los buenos clientes son de tres tipos: aquellos que hacen reservas en AlohAndes al menos una vez al mes,
aquellos que siempre reservan alojamientos costosos (Entiéndase costoso, por ejemplo, como mayor a USD
150 por noche) y aquellos que siempre reservan suites. Esta consulta retorna toda la información de dichos
clientes, incluyendo la que justifica su calificación como buenos clientes. Esta operación es realizada
únicamente por el gerente general de AlohAndes*/

--Buenos clientes tipo 1: Hacen reservas en AlohAndes al menos una vez al mes.--

SELECT m.*, cantidadMesesReservados
FROM (SELECT miembro, count(mes) cantidadMesesReservados
        FROM (SELECT r.idmiembro miembro, EXTRACT(MONTH FROM r.diareserva) mes, count(r.numreserva)
                FROM RESERVAS r
                GROUP BY r.idmiembro, EXTRACT(MONTH FROM r.diareserva)), MIEM_CO_UNIV m
        WHERE miembro = m.id
        GROUP BY miembro), MIEM_CO_UNIV m
WHERE miembro = m.id AND cantidadMesesReservados >= 11;

--Buenos clientes tipo 2: Siempre reservan alojamientos costosos.--

SELECT m.*, cantidadBaratos, cantidadCostosos
FROM (SELECT r.idmiembro miembro, COUNT(r.numreserva) cantidadBaratos,NULL  cantidadCostosos
        FROM RESERVAS r, ALOJAMIENTOS a
        WHERE r.idalojamiento = a.id AND a.precio <= 200000
        GROUP BY r.idmiembro
        UNION
        SELECT r.idmiembro,NULL cantidadBaratos,COUNT(r.numreserva)  cantidadCostosos
        FROM RESERVAS r, ALOJAMIENTOS a
        WHERE r.idalojamiento = a.id AND a.precio >= 200000
        GROUP BY r.idmiembro), MIEM_CO_UNIV m
WHERE m.id = miembro AND cantidadBaratos IS NULL AND cantidadCostosos IS NOT NULL;

--Buenos clientes tipo 3: Siempre reservan Suites--

SELECT m.*, cantidadReservasSuite, cantidadReservasSemisuite, cantidadReservasEstandar
FROM (SELECT r.idmiembro miembro, count(r.numreserva) cantidadReservasSuite, NULL cantidadReservasSemisuite, NULL cantidadReservasEstandar
        FROM RESERVAS r, HABITACIONES h
        WHERE r.idalojamiento = h.idalojamiento AND h.tipohabitacion = 'Suite'
        GROUP BY r.idmiembro, h.tipoophab
        UNION
        SELECT r.idmiembro miembro, NULL cantidadReservasSuite, count(r.numreserva) cantidadReservasSemisuite, NULL cantidadReservasEstandar
        FROM RESERVAS r, HABITACIONES h
        WHERE r.idalojamiento = h.idalojamiento AND h.tipohabitacion = 'Semisuite'
        GROUP BY r.idmiembro, h.tipoophab
        UNION
        SELECT r.idmiembro miembro, NULL cantidadReservasSuite, NULL cantidadReservasSemisuite, count(r.numreserva) cantidadReservasEstandar
        FROM RESERVAS r, HABITACIONES h
        WHERE r.idalojamiento = h.idalojamiento AND h.tipohabitacion = 'Estandar'
        GROUP BY r.idmiembro, h.tipoophab), MIEM_CO_UNIV m
WHERE m.id = miembro AND cantidadReservasSemisuite IS NULL AND cantidadReservasEstandar IS NULL;

