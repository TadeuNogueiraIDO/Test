ALTER TABLE public.tool ADD COLUMN is_listable boolean default true;

INSERT INTO tool
(id, name, description, active, reuse, url, dt_created, dt_updated, dt_deleted, "version", is_free, file_id, appversion, cod_name, is_listable)
VALUES(14, 'Logo e Bio', 'Personalize a logo e a biografia de seu Ido', true, false, 'string', '2022-01-20 18:33:19.844', '2022-01-20 18:33:19.844', NULL, 0, true, 859, '0.0.1', 'LOGOBIO', false);


