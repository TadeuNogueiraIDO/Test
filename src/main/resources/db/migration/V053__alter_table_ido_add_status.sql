ALTER TABLE public.ido DROP COLUMN status ;
ALTER TABLE public.ido DROP COLUMN enabled;
ALTER TABLE public.ido ADD COLUMN status VARCHAR(50);
ALTER TABLE public.ido ADD COLUMN icon_file_id int8; 