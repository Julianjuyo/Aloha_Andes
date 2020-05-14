
-- RFC7- ANALIZAR LA OPERACIÓN DE ALOHANDES
--Para una unidad de tiempo definido (por ejemplo, semana o mes) y un tipo de alojamiento, 
--considerando todo el tiempo de operación de AloHandes, indicar cuáles fueron las fechas de mayor demanda (mayor cantidad de alojamientos ocupados), 
--las de mayores ingresos (mayor cantidad de dinero recibido) y las de menor ocupación.

SELECT * 
FROM RESERVAS r, ALOJAMIENTOS a, OPERADORES o
WHERE r.idalojamiento = a.id;