CREATE TABLE public.analytics_marketing (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp,
	dt_updated timestamp,
	version int4 NOT NULL,
	title varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	CONSTRAINT analytics_marketing_pkey PRIMARY KEY(id)
);

CREATE TABLE public.ido_analytics_marketing (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp,
	version int4 NOT NULL,
	ido_id int8 NOT NULL,
	analytics_marketing_id int8 NOT NULL,
	pixel_value varchar(50),
	is_active boolean,
	CONSTRAINT ido_analytics_marketing_pkey PRIMARY KEY(id),
	CONSTRAINT fk_ido_analytics_marketing_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id),
	CONSTRAINT fk_ido_analytics_marketing_analytics_marketing FOREIGN KEY (analytics_marketing_id) REFERENCES public.analytics_marketing(id)
);


INSERT INTO analytics_marketing(id, dt_created, version, title, description) VALUES (1, '2022-03-23 22:31:35.370', 0, 'Facebook', 'Adicione o seu Facebook Pixel ID para rastrear seus visitantes e melhorar seus anuncios para o Facebook');
INSERT INTO analytics_marketing(id, dt_created, version, title, description) VALUES (2, '2022-03-23 22:31:35.370', 0, 'Google Analytics', 'Adicione o seu ID do Google Analytics para rastrear informações de suas visitas');
INSERT INTO analytics_marketing(id, dt_created, version, title, description) VALUES (3, '2022-03-23 22:31:35.370', 0, 'TikTok', 'Adicione o seu ID do TikTok Pixel  para acompanhar conversões de campanha');
INSERT INTO analytics_marketing(id, dt_created, version, title, description) VALUES (4, '2022-03-23 22:31:35.370', 0, 'Pinterest', 'Preencha o campo com o ID de seu Pinterest Tag para rastrear conversões de suas campanhas no Pinterest');