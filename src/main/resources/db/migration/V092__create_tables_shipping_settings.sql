CREATE TABLE public.shipping_settings (
	id bigserial NOT NULL,
	type_shipping varchar(250),
	devolution varchar(255),
	shop_id int8,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT shipping_settings_pkey PRIMARY KEY (id),
	CONSTRAINT shipping_settings_shop_fk FOREIGN KEY (shop_id) REFERENCES public.shop(id)
);

CREATE TABLE public.own_shipping (
	id bigserial NOT NULL,
	shipping_settings_id int8,
	type_price varchar(250),
	price float8,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT own_shipping_pkey PRIMARY KEY (id),
	CONSTRAINT own_shipping_settings_fk FOREIGN KEY (shipping_settings_id) REFERENCES public.shipping_settings(id)
);

CREATE TABLE public.own_shipping_variation (
	id bigserial NOT NULL,
	own_shipping_id int8,
	label varchar(200),
	price float8,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT own_shipping_variation_pkey PRIMARY KEY (id),
	CONSTRAINT own_shipping_variation_own_shipping_fk FOREIGN KEY (own_shipping_id) REFERENCES public.own_shipping(id)
);

CREATE TABLE public.post_office_shipping (
	id bigserial NOT NULL,
	shipping_settings_id int8,
	zipcode_origin varchar(200),
	type_shipping varchar(200),
	service_option varchar(200),
	adm_code varchar(200),
	adm_password varchar(200),
	optional_services varchar(200),
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT post_office_shipping_pkey PRIMARY KEY (id),
	CONSTRAINT post_office_shipping_settings_fk FOREIGN KEY (shipping_settings_id) REFERENCES public.shipping_settings(id)
);


CREATE TABLE public.local_pickup (
	id bigserial NOT NULL,
	shipping_settings_id int8,
	address varchar(200),
	city varchar(200),
	state varchar(200),
	country varchar(200),
	zipcode varchar(100),
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT local_pickup_pkey PRIMARY KEY (id),
	CONSTRAINT local_pickup_shipping_settings_fk FOREIGN KEY (shipping_settings_id) REFERENCES public.shipping_settings(id)
);



