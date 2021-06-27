CREATE USER api_catalogue WITH ENCRYPTED PASSWORD 'pass_api_catalogue';
CREATE USER api_library WITH ENCRYPTED PASSWORD 'pass_api_library';
CREATE USER sso_keycloak WITH ENCRYPTED PASSWORD 'pass_sso_keycloak';
CREATE DATABASE catalogue;
CREATE DATABASE library;
CREATE DATABASE keycloak;
GRANT ALL PRIVILEGES ON DATABASE catalogue TO api_catalogue;
GRANT ALL PRIVILEGES ON DATABASE library TO api_library;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO sso_keycloak;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

