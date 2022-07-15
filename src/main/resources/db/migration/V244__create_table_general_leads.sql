CREATE TABLE public.general_leads (
	id bigserial NOT NULL,
	activate_download boolean NULL,
	status_leads varchar NULL,
	type_leads varchar NULL,
	title_tool varchar NULL,
	creation_date timestamp NULL,
	user_id int8 NULL,
	CONSTRAINT general_leads_pk PRIMARY KEY (id),
	CONSTRAINT general_leads_fk FOREIGN KEY (user_id) REFERENCES public."user"(id)
);