CREATE TABLE public.address (
	id bigserial NOT NULL,
	address_location varchar(100) NOT NULL,
	street varchar(50) NOT NULL,
	"number" int4 NOT NULL,
	zip_code varchar(50) NOT NULL,
	city varchar(50) NOT NULL,
	state varchar(50) NOT NULL,
	user_id int4 NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id),
	CONSTRAINT fk_address_user FOREIGN KEY (user_id) REFERENCES public.user(id)
);