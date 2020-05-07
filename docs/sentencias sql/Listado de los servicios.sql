--Insertar los servicios--

--PARA LOS HOTELES
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Aumenta dependiendo del numero de personas', 'Capadidad del servicio', 40000,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Trae una bañera', 'Bañera',15000 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'El jacuzzi para ,mejor relajacion', 'Jacuzzi',40000 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'La sala para poder conversar', 'Sala',10000 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, ID_alojamiento, 'con el obetivo de cocinar propios platos', 'Cocineta',40000 ,'N');


-- para hostal y Hotel
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, ID_SERVICIOS_alojamiento, 'Se ofrece una recepcion de 24 horas', 'Recepcion',0 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, ID_SERVICIOS_alojamiento, 'Se debe usar gorro', 'Piscina',0 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, ID_SERVICIOS_alojamiento, 'platos a la crata y menu', 'restaurante',0 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, ID_SERVICIOS_alojamiento, 'para un carro por habitación', 'Parqueadero', 0,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, ID_SERVICIOS_alojamiento, '', 'Wifi', 0,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, ID_SERVICIOS_alojamiento, '', 'TVcable', 0,'N');

--Para las habitaciones ofrecidas por personas naturales
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'son 3 comidas al dia', 'commidas', 0,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'depende', 'Acceso a la concina',0 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Sobre el baño que tendra', 'Baño privado', 0,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Sobre el baño que tendra', 'Baño compartido', 0,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Los muebles que trae', 'Esquema',0 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'los servicios a pagar son luz, teléfono, agua, TV cable y servicio de internet)', 'servicios',60000 ,'N');

--Renta del apartamento
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'cuenta con sillas, sala y comedor', 'amoblado',0 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Se indica si tiene que pagar los servicios publicos', 'Servicios publicos',50000 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Si cuenta con televisor', 'TV',0 ,'N');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Se indica si hay internet', 'internet',0 ,'N');

--Presatar para vivienda universitaria
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Contiene un amoblado basico', 'amoblado',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'para prepara la comida', 'Cocineta',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Servicio de internet 24 horas', 'Internet',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'Sintoniza direct tv', 'TV cable',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'luz, teléfono, agua', 'Servicios publicos',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'con seguridad ecuseguros', 'Porteria 24 horas',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'dos dias a la semana', 'Servicio de aseo',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'en el tercer piso ', 'Servicio se apoyo social', 0,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'se oferen calculos y fisicas', 'Servicio de apoyo academico', 0,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, '', 'Salas de estudio',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, '', 'salas de esparcimiento',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, '', 'gimnasio',0 ,'Y');
INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, 'para las 3 comidas del dia', 'Restaurante',20000 ,'Y');

INSERT INTO SERVICIOS VALUES(ID_SERVICIOS.NEXTVAL, id_alojamiento, '', '', ,'N');


--Para las reservas
INSERT INTO RESERVAS VALUES(ID_RESERVA.NEXTVAL, 155, 12659984, 'CC', '31/01/20', 2 );

--MIEMBRO DE LA COMUNIDAD QUE OFRECE
INSERT INTO MIEM_CO_UNIV VALUES (1012654987, 'CC' , 'Claudia', 'Estudiante');
INSERT INTO MIEM_CO_UNIV VALUES (20163126, 'CC' , 'Maria', 'Egresado');
INSERT INTO MIEM_CO_UNIV VALUES (15962598, 'CC' , 'Esteban', 'Empleado');
INSERT INTO MIEM_CO_UNIV VALUES (32658912, 'TI' , 'Carlos', 'Estudiante');
INSERT INTO MIEM_CO_UNIV VALUES (1265987, 'CC' , 'Blanca', 'Profesor');

--MIEMBRO DUEÑO DE DEPARTAMENTO
INSERT INTO MIEM_CO_UNIV VALUES (10554987, 'CC' ,'Andres', 'Estudiante');
INSERT INTO MIEM_CO_UNIV VALUES (205763, 'CC', 'Hugo', 'Profesor');
INSERT INTO MIEM_CO_UNIV VALUES (738723, 'CC', 'Lucas', 'Estudiante');
INSERT INTO MIEM_CO_UNIV VALUES (3221912, 'CC', 'Lucia', 'Profesor');
INSERT INTO MIEM_CO_UNIV VALUES (891783, 'CC' , 'Sofia', 'Estudiante');
INSERT INTO MIEM_CO_UNIV VALUES (1893289, 'CC' ,'Valeria', 'PadreInvitado');
INSERT INTO MIEM_CO_UNIV VALUES (232326, 'CC' , 'Valentina', 'Estudiante');
INSERT INTO MIEM_CO_UNIV VALUES (15983982, 'CC' ,'Marcos', 'Egresado');
INSERT INTO MIEM_CO_UNIV VALUES (3949843, 'CC' ,'Izan', 'Empleado');
INSERT INTO MIEM_CO_UNIV VALUES (12659984, 'CC' ,'Ana', 'Egresado');







INSERT INTO MIEM_CO_UNIV VALUES (320943, 'CC' ,'ester', 'ProfesorInvitado');
INSERT INTO MIEM_CO_UNIV VALUES (126124, 'CC' ,'Ana', 'ProfesorInvitado');
INSERT INTO MIEM_CO_UNIV VALUES (162007, 'CC' , 'Maria', 'ProfesorInvitado');
INSERT INTO MIEM_CO_UNIV VALUES (129107, 'CC' , 'andrei', 'ProfesorInvitado');
INSERT INTO MIEM_CO_UNIV VALUES (173742, 'CC' , 'camila', 'Estudiante');