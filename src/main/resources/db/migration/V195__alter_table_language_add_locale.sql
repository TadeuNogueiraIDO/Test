ALTER TABLE public.language ADD locale varchar(20);

update public.language set locale = 'es' where id = 1;
update public.language set locale = 'en_US' where id = 2;
update public.language set locale = 'pt_BR' where id = 3;