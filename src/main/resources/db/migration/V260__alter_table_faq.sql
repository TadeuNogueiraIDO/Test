ALTER TABLE public.faq ALTER COLUMN question TYPE text USING question::text;
ALTER TABLE public.faq ALTER COLUMN answer TYPE text USING answer::text;