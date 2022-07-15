CREATE TABLE public.appearance_text (
	id bigserial NOT NULL,
	background_color_id int4 NOT NULL,
	text_font_id int4 NOT NULL,
	font_color_id int4 NULL,
	ido_id int4 NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT appearance_text_pkey PRIMARY KEY (id),
	CONSTRAINT fk_appearance_text_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id),
	CONSTRAINT fk_background_color_id FOREIGN KEY (background_color_id) REFERENCES public.color(id),
	CONSTRAINT fk_font_color_id FOREIGN KEY (font_color_id) REFERENCES public.color(id),
	CONSTRAINT fk_text_font_id FOREIGN KEY (text_font_id) REFERENCES public.text_font(id)
);