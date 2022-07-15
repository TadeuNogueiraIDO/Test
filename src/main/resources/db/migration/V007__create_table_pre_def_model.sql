CREATE TABLE public.pre_def_model (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	name varchar(255) NULL,
	CONSTRAINT pre_def_model_pkey PRIMARY KEY (id)
);