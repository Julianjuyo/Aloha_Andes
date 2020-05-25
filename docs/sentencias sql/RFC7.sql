--RFC7 - ANALIZAR LA OPERACIÓN DE ALOHANDES--

/*Para una unidad de tiempo definido (por ejemplo, semana o mes) y un tipo de alojamiento, considerando todo
el tiempo de operación de AloHandes, indicar cuáles fueron las fechas de mayor demanda (mayor cantidad de
alojamientos ocupados), las de mayores ingresos (mayor cantidad de dinero recibido) y las de menor ocupación.*/

--Unidad de tiempo mes
--Tipo alojamiento Hotel

--Mayor demanda
SELECT to_char(r.diareserva, 'Month' ) mes, EXTRACT(year FROM r.diareserva) anio, count(*) CantidadOcupados
FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
Where r.idalojamiento = a.id AND a.idoperador = o.id AND o.tipooperador = 'Hotel' AND EXTRACT(year FROM r.diareserva) < 2021
group by to_char(r.diareserva, 'Month' ), EXTRACT(year FROM r.diareserva)
order by count(*) desc
FETCH FIRST ROWS ONLY;

--Mayores ingresos
SELECT to_char(r.diareserva, 'Month' ) mes, EXTRACT(year FROM r.diareserva) anio, SUM(a.precio) DineroRecibido
FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
WHERE r.idalojamiento = a.id AND a.idoperador = o.id AND o.tipooperador = 'Hotel' AND EXTRACT(year FROM r.diareserva) < 2021
group by to_char(r.diareserva, 'Month' ), EXTRACT(year FROM r.diareserva)
order by SUM(a.precio) desc
FETCH FIRST ROWS ONLY;

--Menor ocupación
SELECT to_char(r.diareserva, 'Month' ) mes, EXTRACT(year FROM r.diareserva) anio, count(*) CantidadOcupados
FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
Where r.idalojamiento = a.id AND a.idoperador = o.id AND o.tipooperador = 'Hotel' AND EXTRACT(year FROM r.diareserva) < 2021
group by to_char(r.diareserva, 'Month' ), EXTRACT(year FROM r.diareserva)
order by count(*) asc
FETCH FIRST ROWS ONLY;
