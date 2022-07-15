CREATE TABLE IF NOT EXISTS public.newsletter_form(
	id bigserial NOT NULL,
	name varchar(255) NULL,
	email varchar(255) NULL,
	telephone varchar(255) NULL,
	version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	
	CONSTRAINT newsletter_form_pkey PRIMARY KEY (id)
);