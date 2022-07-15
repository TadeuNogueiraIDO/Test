ALTER TABLE public.ido add COLUMN timezone_id int8;
ALTER TABLE public.ido add CONSTRAINT timezone_ido_fk FOREIGN KEY (timezone_id) REFERENCES timezone(id); 