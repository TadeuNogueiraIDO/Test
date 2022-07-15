CREATE TABLE public.appearance_button (
	id bigserial NOT NULL,
	button_shape_id int4 NOT NULL,
	button_color_id int4 NOT NULL,
	text_font_id int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	"version" int4 NOT NULL,
	CONSTRAINT appearance_button_pkey PRIMARY KEY (id),
	CONSTRAINT fk_button_color_id FOREIGN KEY (button_color_id) REFERENCES public.color(id),
	CONSTRAINT fk_button_shape_id FOREIGN KEY (button_shape_id) REFERENCES public.button_shape(id),
	CONSTRAINT fk_text_font_id FOREIGN KEY (text_font_id) REFERENCES public.text_font(id)
);