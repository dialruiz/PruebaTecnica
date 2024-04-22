# Usa la imagen oficial de PostgreSQL como base
FROM postgres

# Establece la contraseña para el usuario postgres
ENV POSTGRES_PASSWORD 12345

# Establece la base de datos predeterminada a crear al iniciar el contenedor
ENV POSTGRES_DB mydatabase

# Copia un script SQL de inicialización a la imagen
COPY init.sql /docker-entrypoint-initdb.d/

# Exponer el puerto por defecto de PostgreSQL
EXPOSE 5432
