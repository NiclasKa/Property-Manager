FROM postgres:12
WORKDIR /app
COPY ./scripts/init.sh /docker-entrypoint-initdb.d
COPY ./scripts/createDatabase.sql ./scripts/createDatabase.sql