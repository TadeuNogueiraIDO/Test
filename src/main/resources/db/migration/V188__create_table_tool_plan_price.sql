CREATE TABLE public.tool_plan_price (
	id bigserial NOT NULL,
	plan_subscription VARCHAR,
	price VARCHAR,
	tool_plan_package_id bigserial NOT NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	CONSTRAINT tool_plan_price_pk PRIMARY KEY (id),
	CONSTRAINT tool_plan_package_fk FOREIGN KEY (tool_plan_package_id) REFERENCES public.tool_plan_package(id)
);