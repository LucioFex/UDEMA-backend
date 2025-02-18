# UDEMA (Backend)
<img src="https://github.com/LucioFex/UDEMA-backend/assets/63211038/c1a53e0e-6e6d-4b60-9a08-30962629d8c7" width=25% height=auto>

Este repositorio contiene el backend de la aplicación UDEMA, una API realizada con Java Spring Boot pensada para administrar datos en plataformas educativas como webcampus, permitiendo a estudiantes y profesores interactuar con cursos, clases y comunidades.

A continuación se detallarán los pasos para correr la aplicación.

## Diagrama de clases

<img src="https://github.com/user-attachments/assets/e5f2e513-06fe-4ff6-bed4-76fe9bbeeef8" width=50% height=auto>

## Preparación de la base de datos

Si es la primera vez que se intenta ejecutar la aplicación, se debe realizar lo siguiente.

```sql
Creación de la base de datos, usuario y asignación de permisos desde root.
CREATE DATABASE udema;
CREATE USER udema_user WITH PASSWORD 'severus_123';
GRANT ALL ON SCHEMA public TO udema_user;
GRANT ALL PRIVILEGES ON DATABASE udema TO udema_user;

-- Conexión local de ejemplo con el usuario a la base de datos:
psql -h localhost -p 5432 -U udema_user udema

-- En caso de tener un error al iniciar la base de datos, revisar los clusters con:
pg_lsclusters

-- Y en caso de que se encuentren incativos, activarlos con:
sudo pg_ctlcluster X main start
-- (Siendo "X" el número de la versión indicada por el cluster).
```

Una vez descargado el proyecto, utilizar los siguiente comandos para levantar el backend:
```bash
mvn compile
mvn spring-boot:run
```

## Endpoints y Autenticación

La autenticación se realiza mediante JWT (JSON Web Tokens). Para acceder a los endpoints protegidos, es necesario crear una cuenta y luego loggearse.

Desde la API se pueden registrar personas con dos posibles role:
- Estudiante
- Profesor.

### Registro de Cuenta

Para realizar el registro se puede utilizar cualquiera de los siguientes endpoints:
- `POST http://localhost:8080/api/students` (para agregar a un usuario con el rol de estudiante).
- `POST http://localhost:8080/api/professors` (para agregar a un usuario con el rol de estudiante).

#### Adicional:

En el ticket se encuentra adjunto un archivo JSON para importar en Insomnia, para tener ya listo un juego de APIs de la aplicación (`Insomnia_2025-02-17.json`).

#### Ejemplo de creación de un alumno y profesor

Alumno

![image](https://github.com/user-attachments/assets/a18ece97-0e05-4f1d-9aad-3f3b68ee8eaa)

Profesor

![image](https://github.com/user-attachments/assets/4982464e-4436-4a5e-a2a0-4618c03d62b2)

#### Endpoint: **POST** `/api/auth/professor/login`

### Login de Estudiante:

#### Endpoint: **POST** `/api/auth/student/login`

#### Request body:

```json
{
  "username": "student_username",
  "password": "student_password"
}
```

#### Response

```json
{
  "token": "...jwt_token..."
}
```

### Login de Profesor:

#### Endpoint: **POST** `/api/auth/professor/login`

#### Request body:

```json
{
  "username": "professor_username",
  "password": "professor_password"
}
```

#### Ejemplo

#### Response

```json
{
  "token": "...jwt_token..."
}
```

#### Ejemplo
![image](https://github.com/user-attachments/assets/d4485b8a-a91e-4032-8132-ab35ffba5fd8)


### Paso posterior al login

#### El token recibido debe ser incluido en el header Authorization como Bearer token para acceder a los endpoints protegidos.

#### Ejemplo

![image](https://github.com/user-attachments/assets/11bae717-c9da-4edc-9a47-244d63ddb220)

## Reinicialización de la Base de Datos
Cada vez que se levanta el backend, la base de datos se reinicia automáticamente para mantener un estado consistente en desarrollo y pruebas (`create-drop` habilitado).

## Documentación de endpoints

Es posible revisar el listado de endpoints disponibles utilizando Swagger.
El endpoint para acceder al mismo es el siguiente:

```bash
http://localhost:8080/swagger-ui/index.html
```

![image](https://github.com/user-attachments/assets/47f425d1-2894-497a-83b3-b8457708726d)

