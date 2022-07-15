CREATE TABLE public.link (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	icon int4 NULL,
	subtitle varchar(255) NULL,
	title varchar(255) NULL,
	ido_id int8 NULL,
	CONSTRAINT link_pkey PRIMARY KEY (id),
	CONSTRAINT fk10ebtyl52hqblhpvlw97j7ygb FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);