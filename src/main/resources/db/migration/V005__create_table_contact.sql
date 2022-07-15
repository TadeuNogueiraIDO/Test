CREATE TABLE public.contact (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	logo varchar(255) NULL,
	mask varchar(255) NULL,
	name varchar(255) NULL,
	CONSTRAINT contact_pkey PRIMARY KEY (id)
);