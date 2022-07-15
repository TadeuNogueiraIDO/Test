ALTER TABLE public."user" RENAME COLUMN validadete_email TO validate_email;
ALTER TABLE public."user" ALTER COLUMN validate_email DROP NOT NULL;