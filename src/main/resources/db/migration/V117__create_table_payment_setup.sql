CREATE TABLE public.payment_setup (
	id bigserial NOT NULL,
	gateway_ido bool NOT NULL DEFAULT false,
	payment_delivery bool NOT NULL DEFAULT false,
	mercado_pago bool NOT NULL DEFAULT false,
	pag_seguro bool NOT NULL DEFAULT false,
	paypal bool NOT NULL DEFAULT false,
	discount_coupon varchar(255) NULL,
	fee_or_tax bool NOT NULL DEFAULT false,
	tip_checkout bool NOT NULL DEFAULT false,
	shop_id int8 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT payment_setup_pk PRIMARY KEY (id),
	CONSTRAINT fk_payment_setup_shop FOREIGN KEY (shop_id) REFERENCES public.shop(id)
);