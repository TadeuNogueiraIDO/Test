ALTER TABLE public.invoice ALTER COLUMN price TYPE varchar USING price::varchar;