CREATE TABLE public.user (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	email varchar(255) NULL,
	name varchar(255) NULL,
	password varchar(255) NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);