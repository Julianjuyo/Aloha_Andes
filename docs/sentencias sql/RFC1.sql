--RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO

SELECT idpe, nom, dinero_Recibido_Anio_Actual, dinero_Recibido_Aniocorrido
FROM
    (SELECT op.id AS idpe, op.nombre AS nom , sum(alo.precio * re.tiempodias) AS dinero_Recibido_Anio_Actual
     FROM  ALOJAMIENTOS alo,  RESERVAS re, OPERADORES op
           WHERE op.id = alo.idoperador
           AND alo.id = re.idalojamiento
           AND re.diareserva BETWEEN '01/01/20' AND '31/12/20'
     GROUP BY op.id, op.nombre) INNER JOIN
    (SELECT op.id idpe1, op.nombre nom1, sum(alo.precio * re.tiempodias) AS dinero_Recibido_Aniocorrido
     FROM  ALOJAMIENTOS alo,  RESERVAS re, OPERADORES op
           WHERE op.id = alo.idoperador
           AND alo.id = re.idalojamiento
           AND re.diareserva BETWEEN '01/01/20' AND '26/05/20'
     GROUP BY op.id, op.nombre)
ON idpe = idpe1 AND nom = nom1;


