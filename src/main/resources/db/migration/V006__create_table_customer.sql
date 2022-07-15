CREATE TABLE public.customer (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	user_id int8 NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id),
	CONSTRAINT fkj8dlm21j202cadsbfkoem0s58 FOREIGN KEY (user_id) REFERENCES public.user(id)
);