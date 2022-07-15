CREATE TABLE public.banner_publicity (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	version int4 NOT NULL,
	images_file_id varchar(20) NULL,
	link varchar(255) NULL,
	title varchar(255) NULL,
	subtitle varchar(255) NULL,
	CONSTRAINT banner_publicity_pkey PRIMARY KEY (id)
);