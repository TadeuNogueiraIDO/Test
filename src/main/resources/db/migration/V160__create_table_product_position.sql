CREATE TABLE public.product_position (
	id bigserial NOT NULL,
	"position" int4 NULL,
	shop_product_id int8 NULL,
	shop_category_id int8 NULL,
	CONSTRAINT product_position_pkey PRIMARY KEY (id),
	CONSTRAINT fk_position_shop_category_id FOREIGN KEY (shop_category_id) REFERENCES public.shop_category(id),
	CONSTRAINT fk_position_shop_product_id FOREIGN KEY (shop_product_id) REFERENCES public.shop_product(id)
);