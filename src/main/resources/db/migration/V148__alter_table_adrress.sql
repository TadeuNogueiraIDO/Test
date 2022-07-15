ALTER TABLE public.address ALTER COLUMN "number" TYPE varchar(10) USING "number"::varchar;
ALTER TABLE public.address DROP COLUMN public_place;
ALTER TABLE public.address ALTER COLUMN address_line1 SET NOT NULL;