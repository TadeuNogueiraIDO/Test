CREATE TABLE public.tool_plan_tool_package (
	id bigserial NOT NULL,
	tool_plan_package_id bigserial NOT NULL,
	tool_package_id bigserial NOT NULL,
	CONSTRAINT tool_plan_tool_package_pk PRIMARY KEY (id),
	CONSTRAINT tool_plan_tool_package_fk_1 FOREIGN KEY (tool_plan_package_id) REFERENCES public.tool_plan_package(id),
	CONSTRAINT tool_plan_tool_package_fk_2 FOREIGN KEY (tool_package_id) REFERENCES public.tool_package(id)
);

