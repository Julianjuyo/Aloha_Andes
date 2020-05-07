SELECT r.idalojamiento , COUNT(*) FROM RESERVAS r
GROUP BY r.IDALOJAMIENTO
ORDER BY idalojamiento DESC
fetch first 20 rows only;