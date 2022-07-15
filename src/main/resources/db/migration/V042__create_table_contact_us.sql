CREATE TABLE IF NOT EXISTS public.contact_us (
	id bigserial NOT NULL,
	name varchar(255) NULL,
	email varchar(255) NULL,
	telephone varchar(255) NULL,
	title varchar(255) NULL,
	text_box varchar(255) NULL,
	version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,	
	CONSTRAINT contact_us_pkey PRIMARY KEY (id)
);