CREATE TABLE public.ido (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	country varchar(255) NULL,
	currency varchar(255) NULL,
	description varchar(255) NULL,
	domain varchar(255) NULL,
	name varchar(255) NULL,
	name_microservice varchar(255) NULL,
	position varchar(255) NULL,
	status bool NULL,
	business_id int8 NULL,
	CONSTRAINT ido_pkey PRIMARY KEY (id),
	CONSTRAINT fknnw0a5nw13ejvp9pm2hvn6bvs FOREIGN KEY (business_id) REFERENCES public.business(id)
);