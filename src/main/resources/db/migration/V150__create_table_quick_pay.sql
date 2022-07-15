CREATE TABLE public.single_quick_pay (
	id bigserial NOT NULL,
	single_price decimal(10,2),
	discount_value decimal(10,2),
	additional_value decimal(10,2),
	observation varchar(250),
	single_quantity int8,
	type varchar(40),
	finalization_type varchar(100),
	has_client_data boolean,
	has_delivery_adress boolean,
	payment_type_id int8,
	ido_id int8,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp,
	version int4 NOT NULL,
	order_number varchar(30),
	client_name varchar(100),
	client_email varchar(100),
	client_phone varchar(100),
	client_document varchar(100),
	client_address varchar(100),
		
	CONSTRAINT single_quick_pay_pkey PRIMARY KEY(id),
	CONSTRAINT fk_single_quick_pay_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id),
	CONSTRAINT fk_single_quick_pay_payment_type FOREIGN KEY (payment_type_id) REFERENCES public.payment_type(id)
);


CREATE TABLE public.single_quick_pay_product(
	id bigserial NOT NULL,
	name varchar(100),
	price decimal(10,2),
	quantity int8,
	observation varchar(250),
	single_quick_pay_id int8,			
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp,
	version int4 NOT NULL,
	CONSTRAINT single_quick_pay_product_pkey PRIMARY KEY(id),
	CONSTRAINT fk_single_quick_pay_id_product FOREIGN KEY (single_quick_pay_id) REFERENCES public.single_quick_pay(id)
)