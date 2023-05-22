# API Pinapp Challenge

API RESTful para crear clientes utilizando Java 8+, Spring Framework, Hibernate y una base de datos HSQLDB

## Tecnologías utilizadas

- Spring Boot
- Hibernate
- HSQLDB

## Configuración de application.properties

Modificar el server port con el puerto que se quiere correr la aplicación.
Cambiar el puerto en el spring.datasource.url por el puerto donde corre la hsqldb.
Modificar el username y password por las credenciales de la base de datos local.
Mantener como update para que únicamente actualice las tablas.
```properties
server.port=8081
spring.datasource.url=jdbc:hsqldb:hsql://localhost:9001/pinapp
spring.datasource.username=SA
spring.datasource.password=
spring.datasource.driver-class-name=org.hsqldb.jdbc.JDBCDriver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

## Scripts para creación de tablas para HyperSQL

```sql
CREATE TABLE clients (
 ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
 FIRST_NAME VARCHAR(255) NOT NULL,
 LAST_NAME VARCHAR(255) NOT NULL,
 AGE VARCHAR(255) NOT NULL,
 BIRTHDAY DATE NOT NULL,
 MODIFIED TIMESTAMP NOT NULL,
 ISACTIVE BOOLEAN NOT NULL
);
```
## Request de ejemplo

Los campos no pueden estar vacíos

```
POST /api/clients
Content-Type: application/json
```

```json
{
  "firstName": "Jhon",
  "lastName": "Doe",
  "age": "28",
  "birthday":"1994-08-05"
}
```

# Response de ejemplo esperado 

```json
{
  "id": 2,
  "firstName": "Jhon",
  "lastName": "Doe",
  "age": "28",
  "birthday": "1994-08-05",
  "modified": "2023-05-22T08:19:02.697+00:00",
  "active": true
}

```

```
GET /api/clients/listclientes
```
# Response de ejemplo esperado

```json
[
  {
    "firstName": "Jhon",
    "lastName": "Doe",
    "age": "28",
    "birthday": "1994-08-05",
    "fechaProbableMuerte": "2074-08-05"
  }
]

```

```
GET /api/clients/kpideclientes
```
# Response de ejemplo esperado

```json
{
  "promedioEdad": 28.0,
  "desviacionEstandar": 0.0
}
```

Para ver la documentación de Swagger de esta API, por favor visite: [SWAGGER](https://app.swaggerhub.com/apis/azamma/pinappchallenge/1.0.0)