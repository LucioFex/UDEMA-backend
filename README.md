# UDEMA-Backend
<img src="https://github.com/LucioFex/UDEMA-backend/assets/63211038/c1a53e0e-6e6d-4b60-9a08-30962629d8c7" width=25% height=auto>

UDEMA es un sitio web con la temática propia de un webcampus universitario.
El propósito de este proyecto es cumplir con los requerimientos de la materia "Programación 3" de la UCEMA (ININF).


```sql
-- Creación de la base de datos, usuario y asignación de permisos desde root.
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
