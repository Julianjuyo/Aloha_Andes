/*
RFC8- ENCONTRAR LOS CLIENTES FRECUENTES

Para un alojamiento dado, encontrar la información de sus clientes frecuentes. se considera frecuente a un cliente si ha utilizado (o tiene reservado) 
ese alojamiento por lo menos en tres ocasiones o por lo menos 15 noches, durante todo el periodo de operación de AlohAndes.
*/

SELECT *
FROM(SELECT id,nom, tipo, timi
     FROM (SELECT COUNT(*) AS numeroDeReservas , r.tiempodias AS tiempo, m.id AS id, m.nombre AS nom, m.tipoid AS tipo, m.tipomiembro AS timi
            FROM ALOJAMIENTOS a, RESERVAS r, MIEM_CO_UNIV m
            WHERE a.id = r.idalojamiento
            AND r.idmiembro = m.id
            GROUP BY m.id, r.tiempodias , m.nombre, m.tipoid, m.tipomiembro)
     WHERE numeroDeReservas >3 OR tiempo > 15
     GROUP BY id,tiempo, nom, tipo, timi);

     
    