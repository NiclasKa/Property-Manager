FROM postgres:12
WORKDIR /app
copy ./scripts/createDatabase.sql ./scripts/createDatabase.sql
copy ./scripts/init.sh /docker-entrypoint-initdb.d/
