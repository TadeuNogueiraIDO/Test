CREATE TABLE public.category (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	name varchar(255) NULL,
	quantity_used int4 NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);