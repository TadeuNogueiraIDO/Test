CREATE TABLE public.product_variation (
	id bigserial NOT NULL,
	name varchar(255) NULL,
	price float8 NULL,
	amount int4 NULL,
	disable_out_of_stock bool NULL DEFAULT false,
	product_color_id int4 null,
	control_stock bool NULL DEFAULT false,
	unit_of_measure varchar(255) NULL,
	Weight float8 NULL,
	"size" float8 NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT product_variation_pk PRIMARY KEY (id)
);

