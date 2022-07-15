CREATE TABLE public.shop_category (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	category_cover bigint NULL,
	hide_product boolean default false,
	ido_id bigint NOT null,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT shop_category_pk PRIMARY KEY (id),
	CONSTRAINT fk_shop_category_ido FOREIGN KEY (ido_id) references ido(id)
);