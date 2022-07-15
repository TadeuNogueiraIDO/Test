CREATE TABLE discount_coupon (
	id bigserial NOT NULL,
	name varchar NULL,
	coupon_code varchar NOT NULL,
	date_expiration_date date NULL,
	discount_coupon varchar NULL,
	minimum_value_condition boolean NULL,
	minimum_value decimal NULL,
	shop_id int8 NULL,
	value_discount decimal NULL,
    dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT new_coupon_pk PRIMARY KEY (id),
        CONSTRAINT fk_cupoun_shop FOREIGN KEY (shop_id) REFERENCES shop(id)
);