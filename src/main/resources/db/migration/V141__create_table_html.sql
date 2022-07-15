CREATE TABLE public.embed_html (
    id bigserial NOT NULL,
    title varchar(100) NOT NULL,
    code text NOT NULL,
    ido_id int8,
    dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
    CONSTRAINT embed_html_pk PRIMARY KEY (id),
    CONSTRAINT fk_embed_html_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);

insert into tool(id, "name", description, active, reuse, url, dt_created, "version", is_free, appversion, cod_name)
values (13,'Embed HTML', 'Personalize seu Ido inserindo conteúdo HTML', true, true, 'String', '2022-01-20 18:13:20.137', 0, true, '0.0.1', 'HTML'); 





