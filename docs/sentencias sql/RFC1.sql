--RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL ANIO ACTUAL Y EL ANIO CORRIDO

SELECT idpe, nom, dinero_Recibido_Anio_Actual, dinero_Recibido_Aniocorrido
FROM
    (SELECT op.id AS idpe, op.nombre AS nom , sum(alo.precio * re.tiempodias) AS dinero_Recibido_Anio_Actual
     FROM  ALOJAMIENTOS alo,  RESERVAS re, OPERADORES op
           WHERE op.id = alo.idoperador
           AND alo.id = re.idalojamiento
           AND EXTRACT(YEAR FROM re.diareserva) = 2020
     GROUP BY op.id, op.nombre) INNER JOIN
    (SELECT op.id idpe1, op.nombre nom1, sum(alo.precio * re.tiempodias) AS dinero_Recibido_Aniocorrido
     FROM  ALOJAMIENTOS alo,  RESERVAS re, OPERADORES op
           WHERE op.id = alo.idoperador
           AND alo.id = re.idalojamiento
           AND EXTRACT(YEAR FROM re.diareserva) = 2019
     GROUP BY op.id, op.nombre)
ON idpe = idpe1 AND nom = nom1;



