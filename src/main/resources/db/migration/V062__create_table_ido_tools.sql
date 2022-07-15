CREATE TABLE IF NOT EXISTS public.ido_tools (
	id bigserial NOT NULL,
	status varchar(50) NOT NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	ido_id int8 NOT NULL,
	tool_id int8 NOT NULL,
	CONSTRAINT ido_tools_pkey PRIMARY KEY (id),
	CONSTRAINT fk_ido_tools_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id),
	CONSTRAINT fk_ido_tools_tool FOREIGN KEY (tool_id) REFERENCES public.tool(id)
);

