CREATE TABLE IF NOT EXISTS public.ido_tools_position (
	id bigserial NOT NULL,
	ido_id int8,
	tool_cod_name varchar(50),
	generic_tool_id int8,
	position int4,
	CONSTRAINT ido_position_pkey PRIMARY KEY (id),
	CONSTRAINT fk_ido_tools_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);