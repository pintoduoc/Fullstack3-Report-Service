-- liquibase formatted sql

-- changeset benja:2
INSERT INTO reporte_incendio (latitud, longitud, descripcion, url_evidencia, estado) VALUES
                                                                                         (-33.456, -70.648, 'Humo visible en ladera cerro Manquehue', 'http://evidencia.cl/1.jpg', 'PENDIENTE'),
                                                                                         (-33.412, -70.612, 'Foco activo cerca de zona residencial', 'http://evidencia.cl/2.jpg', 'EN_COMBATE'),
                                                                                         (-33.489, -70.598, 'Quema de pastizales en sitio eriazo', 'http://evidencia.cl/3.jpg', 'CONTROLADO'),
                                                                                         (-33.510, -70.510, 'Fuego extinguido por bomberos', 'http://evidencia.cl/4.jpg', 'EXTINGUIDO'),
                                                                                         (-33.420, -70.580, 'Incendio forestal sector norte', 'http://evidencia.cl/5.jpg', 'EN_COMBATE'),
                                                                                         (-33.390, -70.600, 'Basural clandestino en llamas', 'http://evidencia.cl/6.jpg', 'PENDIENTE'),
                                                                                         (-33.430, -70.620, 'Foco extinguido tras lluvia', 'http://evidencia.cl/7.jpg', 'EXTINGUIDO'),
                                                                                         (-33.460, -70.650, 'Rebrote de incendio forestal', 'http://evidencia.cl/8.jpg', 'EN_COMBATE'),
                                                                                         (-33.470, -70.630, 'Fuego controlado por brigada Alfa', 'http://evidencia.cl/9.jpg', 'CONTROLADO'),
                                                                                         (-33.480, -70.610, 'Aviso de fuego no confirmado', 'http://evidencia.cl/10.jpg', 'PENDIENTE');