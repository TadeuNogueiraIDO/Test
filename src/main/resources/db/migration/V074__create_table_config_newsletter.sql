ALTER TABLE public.newsletter_form drop column ido_id;

CREATE TABLE public.config_newsletter_form (
	id bigserial NOT NULL,
	title varchar(255) NULL,
	subtitle varchar(255) NULL,
	datauser varchar(100) NULL,
	ido_id int8,
	icon_file_id int8 NULL,
	version int4 NOT NULL,
	animated boolean default 'false',
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,	
	CONSTRAINT config_newsletter_form_pkey PRIMARY KEY (id),
	CONSTRAINT fk_config_newsletter_form_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);

ALTER TABLE public.newsletter_form ADD COLUMN config_newsletter_form_id int8;
ALTER TABLE public.newsletter_form ADD CONSTRAINT fk_config_newsletter_form_config_fk FOREIGN KEY (config_newsletter_form_id) REFERENCES public.config_newsletter_form(id)