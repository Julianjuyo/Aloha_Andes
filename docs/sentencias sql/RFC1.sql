--RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO

SELECT idpe, nom, dinero_Recibido_Año_Actual, dinero_Recibido_Añocorrido
FROM
    (SELECT op.id AS idpe, op.nombre AS nom , sum(alo.precio * re.tiempodias) AS dinero_Recibido_Año_Actual
     FROM  ALOJAMIENTOS alo,  RESERVAS re, OPERADORES op
           WHERE op.id = alo.idoperador
           AND alo.id = re.idalojamiento
           AND re.diareserva BETWEEN '01/01/20' AND '31/12/20'
     GROUP BY op.id, op.nombre
     ORDER BY dinero_Recibido_Año_Actual DESC),

    (SELECT op.id, op.nombre , sum(alo.precio * re.tiempodias) AS dinero_Recibido_Añocorrido
     FROM  ALOJAMIENTOS alo,  RESERVAS re, OPERADORES op
           WHERE op.id = alo.idoperador
           AND alo.id = re.idalojamiento
           AND re.diareserva BETWEEN '01/01/20' AND '26/05/20'
     GROUP BY op.id, op.nombre
     ORDER BY dinero_Recibido_Añocorrido DESC) ;


