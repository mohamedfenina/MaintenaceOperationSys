-- Create the maintenance_db database if it doesn't exist
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'maintenance_db')
BEGIN
    CREATE DATABASE maintenance_db;
END
GO

USE maintenance_db;
GO

-- Check if the login exists, if not create it
IF NOT EXISTS (SELECT name FROM sys.server_principals WHERE name = 'mohamed_fenina')
BEGIN
    -- Create login
    CREATE LOGIN [mohamed_fenina] WITH PASSWORD = 'Itbs@Dev123';
END
GO

-- Check if the database user exists, if not create it
IF NOT EXISTS (SELECT name FROM sys.database_principals WHERE name = 'mohamed_fenina')
BEGIN
    -- Create database user
    CREATE USER [mohamed_fenina] FOR LOGIN [mohamed_fenina];
    
    -- Grant permissions to the user
    ALTER ROLE db_owner ADD MEMBER [mohamed_fenina];
END
GO

-- If you have specific tables and permissions, you can add them here
-- For example:
-- GRANT SELECT, INSERT, UPDATE, DELETE ON [YourTable] TO [mohamed_fenina];
