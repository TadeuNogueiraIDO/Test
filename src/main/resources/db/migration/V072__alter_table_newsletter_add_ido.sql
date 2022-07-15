ALTER TABLE public.newsletter_form ADD COLUMN ido_id int8;
ALTER TABLE public.newsletter_form ADD CONSTRAINT newsletter_form_ido_fk FOREIGN KEY (ido_id) REFERENCES public.ido(id);