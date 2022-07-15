CREATE TABLE public.product_type (
	id bigserial NOT NULL,
	icon int8 NULL,
	"type" varchar(30) NULL,
	description text NULL,
	title varchar(20) NULL,
	CONSTRAINT product_type_pkey PRIMARY KEY (id)
);

INSERT into product_type (id, icon, "type", description) values
(1, 2020, 'PHYSICAL', 'Produtos entregues fisicamente, como: vestuário, comida, artesanato etc.'),
(2, 2019, 'DIGITAL', 'Qualquer produto que seja acessado por link e enviado virtualmente.');