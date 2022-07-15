ALTER TABLE public.attached_pdf DROP COLUMN business_id;
ALTER TABLE public.config_contact_us DROP COLUMN business_id;

ALTER TABLE public.attached_pdf ADD COLUMN ido_id int8;
ALTER TABLE public.config_contact_us ADD COLUMN ido_id int8;

ALTER TABLE public.attached_pdf add CONSTRAINT fk_attached_pdf_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id);
ALTER TABLE public.config_contact_us add CONSTRAINT fk_config_contact_us_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id);