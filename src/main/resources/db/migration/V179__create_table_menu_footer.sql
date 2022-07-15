CREATE TABLE public.menu_footer (
	id bigserial NOT NULL,
	logo int8 NULL,
	unpin_menu bool NOT NULL,
	activate_footer bool NOT NULL,
	ido_id int8 NOT NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	CONSTRAINT menu_footer_pkey PRIMARY KEY (id),
	CONSTRAINT fk_ido_id_menu_footer FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);

CREATE TABLE public.item_menu_footer (
	id bigserial NOT NULL,
	type_item varchar(50) NOT NULL,
	"label" varchar(100) NOT NULL,
	external_link varchar(200) NULL,
	tool_id int8 NULL,
	shop_category_id int8 NULL,
	menu_footer_id int8 NOT NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	hide bool NOT NULL DEFAULT false,
	CONSTRAINT item_menu_footer_pkey PRIMARY KEY (id),
	CONSTRAINT fk_menu_footer_id_item_menu_footer FOREIGN KEY (menu_footer_id) REFERENCES public.menu_footer(id),
	CONSTRAINT fk_shop_category_id_item_menu_footer FOREIGN KEY (shop_category_id) REFERENCES public.shop_category(id),
	CONSTRAINT fk_tool_id_item_menu_footer FOREIGN KEY (tool_id) REFERENCES public.tool(id)
);
