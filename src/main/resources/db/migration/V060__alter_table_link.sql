ALTER TABLE public.link DROP COLUMN url_icon;
ALTER TABLE public.link ADD icon int8 NULL;
