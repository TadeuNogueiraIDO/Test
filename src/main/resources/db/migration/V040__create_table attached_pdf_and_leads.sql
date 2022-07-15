CREATE TABLE public.attached_pdf (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	
	title varchar(255) NOT NULL,	
	subtitle varchar(255) NOT NULL,	
	pdf_file_id int8 NULL,
	icon_file_id int8 NULL,
	data_user varchar(100) NULL,
	business_id int8 NULL,
	
	CONSTRAINT attached_pdf_pkey PRIMARY KEY (id),
	CONSTRAINT fk_attached_pdf_business FOREIGN KEY (business_id) REFERENCES public.business(id)
);

CREATE TABLE public.attached_pdf_leads (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	name varchar(100) NOT NULL,	
	email varchar(100) NOT NULL,	
	phone varchar(50) NULL,
	attached_pdf_id int8 NULL,
	CONSTRAINT attached_pdf_leads_pkey PRIMARY KEY (id),
	CONSTRAINT fk_attached_pdf_attached_pdf_leads FOREIGN KEY (attached_pdf_id) REFERENCES public.attached_pdf(id)
);