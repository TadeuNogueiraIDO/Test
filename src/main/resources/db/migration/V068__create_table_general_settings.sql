CREATE TABLE public."language" (
	id bigserial NOT NULL,
	"name" VARCHAR(50) NOT NULL,
	CONSTRAINT language_pkey PRIMARY KEY (id)
	);
	
INSERT into "language" (id, "name") VALUES
(1, 'Espanhol'),
(2, 'Inglês'),
(3, 'Português');

CREATE TABLE public.general_settings (
	id bigserial NOT NULL,
	language_id int4 NOT NULL,
	sensitive_content Boolean NOT NULL,
	receive_report_by_email Boolean NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	"version" int4 NOT NULL,
	CONSTRAINT general_settings_pkey PRIMARY KEY (id),
	CONSTRAINT fk_language_id FOREIGN KEY (language_id) REFERENCES public."language"(id)
);