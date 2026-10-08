# API REST de Hotel - SIS414

Spring Boot, Spring Data JPA, PostgreSQL y Swagger. Java 21.

Hotel: id (Long, generado), nombre (String), ciudad (String), habitaciones (Integer), categoria (Integer).

## Enlaces de entrega

- [Codigo en GitHub](https://github.com/DannerCF/sis414-hoteles)
- [Swagger en Render](https://sis414-hoteles.onrender.com/swagger-ui/index.html)
- [Listado de hoteles](https://sis414-hoteles.onrender.com/api/hoteles)

## Endpoints

| Metodo | Ruta | Resultado |
|---|---|---|
| POST | /api/hoteles | Crear, 201 |
| GET | /api/hoteles | Listar, 200 |
| GET | /api/hoteles/{id} | Buscar, 200 o 404 |
| PUT | /api/hoteles/{id} | Actualizar, 200 o 404 |
| DELETE | /api/hoteles/{id} | Eliminar, 204 o 404 |

Ejemplo para POST y PUT:
```json
{"nombre":"Hotel Central","ciudad":"La Paz","habitaciones":20,"categoria":3}
```

Crear primero y usar el id devuelto. Un 404 indica que el hotel solicitado no existe.

## Ejecutar

Configurar DB_URL (jdbc:postgresql://HOST:5432/DATABASE), DB_USERNAME y DB_PASSWORD.
Ejecutar `./gradlew bootRun` o `gradlew.bat bootRun` en Windows.
Swagger: `/swagger-ui/index.html`.

## Render

Crear PostgreSQL y Web Service con Docker en la misma region. Configurar las tres variables usando los datos internos de la base. El puerto usa PORT de Render. Health Check Path: `/api/hoteles`. No incluye HealthController ni endpoint /health.

## Pruebas

`./gradlew test bootJar`
