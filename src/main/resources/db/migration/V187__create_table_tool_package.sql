CREATE TABLE public.tool_package (
	id bigserial NOT NULL,
	tool_id bigserial NOT NULL,
	addition int NULL,
	description varchar NULL,
	resource_limitation int NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	CONSTRAINT tool_package_pk PRIMARY KEY (id),
	CONSTRAINT tool_package_fk FOREIGN KEY (tool_id) REFERENCES public.tool(id)
);
