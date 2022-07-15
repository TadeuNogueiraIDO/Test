CREATE TABLE public.user_plan_package (
	id bigserial NOT NULL,
	expiration_date timestamp NOT NULL,
	payment_status varchar NOT NULL,
	tool_type varchar NOT NULL,
	plan_subscription varchar NULL,
	tool_plan_package_id bigserial NOT NULL,
	ido_id bigserial NOT NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	CONSTRAINT user_plan_package_pk PRIMARY KEY (id),
	CONSTRAINT user_plan_package_fk FOREIGN KEY (ido_id) REFERENCES public.ido(id),
	CONSTRAINT user_plan_package_fk_1 FOREIGN KEY (tool_plan_package_id) REFERENCES public.tool_plan_package(id)
);