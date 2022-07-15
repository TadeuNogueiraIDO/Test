ALTER TABLE public."user" ADD country_id int8 NULL;
ALTER TABLE public."user" ADD CONSTRAINT user_country_fk FOREIGN KEY (country_id) REFERENCES public.country(id);
