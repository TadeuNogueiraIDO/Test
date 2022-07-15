CREATE TABLE public.idolink_profile (
	id bigserial NOT NULL,
	"name" varchar(100) NOT NULL,
	url varchar(200) NOT NULL,
	cod_name varchar(200) NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT idolink_perfil_pkey PRIMARY KEY (id)
);
