CREATE TABLE public.config_contact_us (
	id bigserial NOT NULL,
	title varchar(255) NULL,
	subtitle varchar(255) NULL,
	datauser varchar(100) NULL,
	business_id int8,
	version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,	
	CONSTRAINT config_contact_us_pkey PRIMARY KEY (id),
	CONSTRAINT fk_config_contact_us_business FOREIGN KEY (business_id) REFERENCES public.business(id)
);

ALTER TABLE public.contact_us ADD COLUMN config_contact_us_id int8;
ALTER TABLE public.contact_us add CONSTRAINT fk_config_contact_us_contact_us FOREIGN KEY (config_contact_us_id) REFERENCES public.config_contact_us(id)