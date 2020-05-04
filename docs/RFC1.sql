--RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL A�O ACTUAL Y EL A�O CORRIDO


SELECT op.id, op.nombre , sum(ap.precio * re.tiempodias) AS dinero_Recibido
FROM  APARTAMENTOS ap,  RESERVAS re, OPERADORES op
WHERE op.id = ap.dueno
AND ap.idalojamiento= re.idalojamiento
group by op.id, op.nombre;


SELECT op.id,  sum(habi.precio * re.tiempodias) AS dinero_Recibido
FROM  HABITACIONES habi,  RESERVAS re, OPERADORES op
WHERE op.id = habi.idalojamiento
AND habi.idalojamiento= re.idalojamiento
group by op.id, op.nombre;


SELECT op.id, op.nombre, sum(vi.precio * re.tiempodias) AS dinero_Recibido
FROM  VIVIENDACOMUNIDAD vi,  RESERVAS re, OPERADORES op
WHERE op.id = vi.idalojamiento
AND vi.idalojamiento= re.idalojamiento
group by op.id, op.nombre;