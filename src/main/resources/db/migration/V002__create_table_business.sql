CREATE TABLE public.business (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	name varchar(255) NULL,
	user_id int8 NULL,
	CONSTRAINT business_pkey PRIMARY KEY (id),
	CONSTRAINT fkjkjjimeu5p0gv4orhue734xni FOREIGN KEY (user_id) REFERENCES public.user(id)
);