CREATE TABLE public.presentation_shop (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	activate_name boolean default false,
	file_id bigint NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT presentation_shop_pk PRIMARY KEY (id)
);