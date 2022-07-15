
CREATE TABLE public.video_banner (
	id bigserial NOT NULL,
    title varchar(250) NOT NULL,
    link varchar NOT NULL,
	ido_id bigint NOT NULL,
	dt_created timestamp NOT NULL,
	dt_updated timestamp NULL,
	dt_deleted timestamp NULL,
	version bigserial NOT NULL,
	CONSTRAINT video_banner_pk PRIMARY KEY (id),
    CONSTRAINT video_banner_ido FOREIGN KEY (ido_id) references public.ido(id)
);