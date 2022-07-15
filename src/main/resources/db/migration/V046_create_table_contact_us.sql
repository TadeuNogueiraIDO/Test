CREATE TABLE public.contact_us (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	email varchar(255) NULL,
	telephone varchar(255) NULL,
	title varchar(255) NULL,
	text_box varchar(255) NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	config_contact_us_id int8 NULL,
	CONSTRAINT contact_us_pkey PRIMARY KEY (id),
	CONSTRAINT fk_config_contact_us_contact_us FOREIGN KEY (config_contact_us_id) REFERENCES public.config_contact_us(id)
);