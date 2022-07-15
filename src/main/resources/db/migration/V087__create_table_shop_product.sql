CREATE TABLE public.shop_product (
	id bigserial NOT NULL,
	name varchar(255) NOT NULL,
	description varchar(255),
	shop_category_id int8,
	main_image_file_id int8,
	additional_images_file_id varchar(255),
	is_vitrine_show boolean,
	is_digital boolean,
	has_variation boolean,
	digital_url varchar(100),
	digital_orientation varchar(255),
	version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,	
	
	CONSTRAINT shop_product_pkey PRIMARY KEY (id),
	CONSTRAINT shop_category_shop_product_fk FOREIGN KEY (shop_category_id) REFERENCES public.shop_category(id)
);

