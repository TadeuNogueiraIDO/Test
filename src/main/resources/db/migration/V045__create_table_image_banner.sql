CREATE TABLE public.image_banner (
	id bigserial NOT NULL,
    title varchar(250) NOT NULL,
    action int4 NOT NULL,
    field varchar NOT NULL,
	file_id varchar NOT NULL,
	ido_id bigint NOT NULL,
	dt_created timestamp NOT NULL,
	dt_updated timestamp NULL,
	dt_deleted timestamp NULL,
	version bigserial NOT NULL,
	CONSTRAINT image_banner_pk PRIMARY KEY (id),
    CONSTRAINT image_banner_ido FOREIGN KEY (ido_id) references public.ido(id)
);