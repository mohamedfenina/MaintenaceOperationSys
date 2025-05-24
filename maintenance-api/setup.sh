#!/bin/bash
set -e

# Wait for SQL Server to be ready
sleep 15s

# Execute the SQL init script
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P $SA_PASSWORD -d master -i /docker-entrypoint-initdb.d/init-db.sql

echo "Database initialization completed."
