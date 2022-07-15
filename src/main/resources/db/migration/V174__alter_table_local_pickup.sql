ALTER TABLE public.local_pickup RENAME COLUMN address TO address_line_1;
ALTER TABLE public.local_pickup RENAME COLUMN address2 TO address_line_2;
ALTER TABLE public.local_pickup ADD country_id int4 null;
ALTER TABLE public.local_pickup add CONSTRAINT fk_pickup_country FOREIGN KEY (country_id) REFERENCES public.country(id);
