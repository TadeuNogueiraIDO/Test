DROP TABLE IF EXISTS public.address;

CREATE TABLE public.address (
	id bigserial NOT NULL,
	address_name varchar(100) NOT NULL,
	city varchar(50) NOT NULL,
	zip_code varchar(50) NOT NULL,
	public_place varchar(50) NULL,
	district varchar(50) NULL,
	"number" int4 NULL,
	complement varchar(50) NULL,
	state_id int4 NULL,
	address_line1 varchar(100) NULL,
	address_line2 varchar(100) NULL,
	type_of_house varchar(50) NULL,
	state varchar(50) NULL,
	user_id int4 NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	country_id int4 NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id),
	CONSTRAINT fk_address_user FOREIGN KEY (user_id) REFERENCES public."user"(id),
	CONSTRAINT fk_state_address FOREIGN KEY (state_id) REFERENCES public.state(id)
);