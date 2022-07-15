CREATE TABLE public.tool_plan_package (
	id bigserial NOT NULL,
	tool_type varchar NOT NULL,
	active boolean NOT NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	CONSTRAINT tool_plan_package_pk PRIMARY KEY (id)
);
