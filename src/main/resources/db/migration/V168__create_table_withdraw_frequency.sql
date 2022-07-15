CREATE TABLE wallet_withdraw_frequency (
	id bigserial NOT NULL,
	period_frequency varchar NOT NULL,
	week_day varchar DEFAULT NULL,
	month_day int8 DEFAULT NULL,
	"version" int4 NULL,
	dt_created timestamp NULL,
	dt_updated timestamp NULL,
	dt_deleted timestamp NULL,
	CONSTRAINT wallet_withdraw_frequency_pk PRIMARY KEY (id)
);
