CREATE TABLE public.invoice (
	id bigserial NOT NULL,
	payment_status varchar NOT NULL,
	price bigint NULL,
	"month" varchar NOT Null,
	"year" bigint NOT NULL,
 	user_id bigserial NOT NULL,
	"version" int4 NULL,
	dt_created timestamp NULL,
	dt_updated timestamp NULL,
	dt_deleted timestamp NULL,
	CONSTRAINT invoice_pk PRIMARY KEY (id),
	CONSTRAINT invoice_fk FOREIGN KEY (user_id) REFERENCES public."user"(id)
);
