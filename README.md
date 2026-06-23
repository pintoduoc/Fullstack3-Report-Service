# Report Service - Microservicio de Reportes de Incendio

Spring Boot 3.4.x para la gestión de reportes de incendio del sistema de emergencias Valle del Sol.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/reportes` | Listar todos los reportes |
| GET | `/api/reportes/{id}` | Obtener reporte por ID |
| POST | `/api/reportes` | Crear reporte |
| PUT | `/api/reportes/{id}` | Actualizar estado del reporte |
| DELETE | `/api/reportes/{id}` | Eliminar reporte |
| GET | `/api/reportes/estadisticas` | Obtener estadísticas (dashboard) |

## Tecnologías

- Spring Boot 3.4.4 / Java 17
- Spring Data JPA (MySQL)
- Eureka Client
- JaCoCo (cobertura ≥ 60%)

## Tests

```bash
mvnw test
```
