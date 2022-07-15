CREATE TABLE public.user_password_recovery (
	id bigserial NOT NULL,
	code varchar NOT NULL,
	user_id bigint NOT NULL,
	dt_expiration timestamp NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);