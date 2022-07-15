CREATE TABLE public.button_animation (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	description varchar(255) NULL,
	name varchar(255) NULL,
	CONSTRAINT button_animation_pkey PRIMARY KEY (id)
);