CREATE ROLE order_service WITH
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    LOGIN
    NOREPLICATION
    NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD 'YFmKGKzHn2';

-- DROP SCHEMA auth_service;

CREATE SCHEMA order_service AUTHORIZATION order_service;


-- Permissions

GRANT ALL ON SCHEMA order_service TO order_service;

CREATE TABLE order_service.message (
	uuid varchar(100) NOT NULL,
	CONSTRAINT message_pkey PRIMARY KEY (uuid)
);

ALTER TABLE order_service.message OWNER TO order_service;
GRANT ALL ON TABLE order_service.message TO order_service;


CREATE TABLE order_service.order (
   id bigserial NOT NULL,
   user_name varchar(50) NOT NULL,
   total_amount numeric(16,2) NOT NULL DEFAULT 0,
   total_discount numeric(16,2) NOT NULL DEFAULT 0,
   total_quantity int8 NOT NULL DEFAULT 0,
   CONSTRAINT order_pkey PRIMARY KEY (id)
);

ALTER TABLE order_service.order OWNER TO order_service;
GRANT ALL ON TABLE order_service.order TO order_service;