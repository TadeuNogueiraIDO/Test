CREATE TABLE public.model_param (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	name varchar(255) NULL,
	type varchar(255) NULL,
	CONSTRAINT model_param_pkey PRIMARY KEY (id)
);