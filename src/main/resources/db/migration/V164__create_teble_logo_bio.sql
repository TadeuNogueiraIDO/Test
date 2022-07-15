CREATE TABLE public.logo_bio (
	id bigserial NOT NULL,
	file_id int4 NULL,
	logo bool Not NULL,
	"name" varchar(100) NULL,
	name_boo bool NOT NULL,
	bio varchar(100) NULL,
	bio_boo bool NOT NULL,
	ido_id int4 NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT logo_bio_pkey PRIMARY KEY (id),
	CONSTRAINT fk_logo_bio_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);