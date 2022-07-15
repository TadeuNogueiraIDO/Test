ALTER TABLE public.ido add COLUMN language_id int8; 
ALTER TABLE public.ido add COLUMN hide_Idolink boolean; 
ALTER TABLE public.ido add COLUMN sensitive_content boolean; 
ALTER TABLE public.ido add CONSTRAINT language_ido_fk FOREIGN KEY (language_id) REFERENCES language(id);