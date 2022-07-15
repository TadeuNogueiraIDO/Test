CREATE TABLE public.shop_quick_pay (
	id bigserial NOT NULL,
	discount_value decimal(10,2),
	additional_value decimal(10,2),
	finalization_type varchar(100),
	has_client_data boolean,
	has_delivery_adress boolean,
	order_number varchar(30),
	status_payment varchar(50),
	status_sending varchar(50),
	payment_type_id int8, 	
	another_payment_type varchar(200),
	user_id int8,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp,
	version int4 NOT NULL,
	client_name varchar(100),
	client_email varchar(100),
	client_phone varchar(100),
	client_document varchar(100),
	client_address varchar(100),
	CONSTRAINT shop_quick_pay_pkey PRIMARY KEY(id),
	CONSTRAINT fk_shop_quick_pay_user FOREIGN KEY (user_id) REFERENCES public.user(id),
	CONSTRAINT fk_shop_quick_pay_payment_type FOREIGN KEY (payment_type_id) REFERENCES public.payment_type(id)
);


CREATE TABLE public.shop_quick_pay_product(
	id bigserial NOT NULL,
	product_variation_id int8 NOT NULL,
	observation varchar(250),
	quantity int8,
	shop_quick_pay_id int8,			
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp,
	version int4 NOT NULL,
	CONSTRAINT shop_quick_pay_product_pkey PRIMARY KEY(id),
	CONSTRAINT fk_shop_quick_pay_id_product FOREIGN KEY (shop_quick_pay_id) REFERENCES public.shop_quick_pay(id),
	CONSTRAINT fk_shop_quick_pay_product_variation FOREIGN KEY (product_variation_id) REFERENCES public.product_variation(id)
)





