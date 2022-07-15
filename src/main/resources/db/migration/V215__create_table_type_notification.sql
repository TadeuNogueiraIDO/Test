CREATE TABLE public.type_notification (
	id bigserial NOT NULL,
	name varchar NOT NULL,
	description varchar NOT NULL,
	CONSTRAINT type_notification_pk PRIMARY KEY (id)
);
