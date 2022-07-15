ALTER TABLE public.notification ADD id_newsletter_form int8 NULL;
ALTER TABLE public.notification RENAME COLUMN id_pdf TO id_lead;
ALTER TABLE public.type_notification RENAME COLUMN "name" TO title;
