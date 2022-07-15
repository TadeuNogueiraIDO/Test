CREATE TABLE public.faq (
	id bigserial NOT NULL,
	question varchar(255) NOT NULL,
	answer varchar(255) NULL,
	url_youtube varchar(150) NULL,
	file_id int8 NULL,
	ido_id int8 NOT NULL,
	CONSTRAINT faq_pkey PRIMARY KEY (id),
	CONSTRAINT faq_ido_fk FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);