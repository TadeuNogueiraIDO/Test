CREATE TABLE public.config_faq (
	id bigserial NOT NULL,
	title varchar(255) NOT NULL,
	subtitle varchar(255),
	icon_id int8,
	type_icon varchar(50),
	ido_id int8,
	version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,	
	
	CONSTRAINT config_faq_pkey PRIMARY KEY (id),
	CONSTRAINT config_faq_ido_fk FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);

ALTER TABLE public.faq drop column url_youtube;
ALTER TABLE public.faq drop column file_id;
ALTER TABLE public.faq drop column ido_id;
ALTER TABLE public.faq add column config_faq_id int8;
ALTER TABLE public.faq add CONSTRAINT faq_config_faq_fk FOREIGN KEY (config_faq_id) REFERENCES public.config_faq(id);
