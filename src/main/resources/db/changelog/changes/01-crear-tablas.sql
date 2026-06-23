-- liquibase formatted sql

-- changeset benja:1
CREATE TABLE reporte_incendio (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  latitud DOUBLE,
                                  longitud DOUBLE,
                                  descripcion VARCHAR(255),
                                  url_evidencia VARCHAR(255),
                                  estado VARCHAR(50)
);