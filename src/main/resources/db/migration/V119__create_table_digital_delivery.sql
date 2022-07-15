CREATE TABLE public.digital_delivery (
	id bigserial NOT NULL,
	shipping_settings_id int8,
	password varchar(200),
	enabled_password boolean,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT digital_delivery_pkey PRIMARY KEY (id),
	CONSTRAINT digital_delivery_shipping_settings_fk FOREIGN KEY (shipping_settings_id) REFERENCES public.shipping_settings(id)
);