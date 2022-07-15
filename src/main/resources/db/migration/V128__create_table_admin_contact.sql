CREATE TABLE public.admin_contact (
	id bigserial NOT NULL,
	title varchar(100) NOT NULL,
	description text NOT NULL,
	main_icon int8 NULL,
	secondary_icon int8 NULL,
	url varchar(200) NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT admin_contact_pkey PRIMARY KEY (id)
);