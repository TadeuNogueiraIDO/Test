CREATE TABLE public.appearance_cards (
	id bigserial NOT NULL,
	format_card_id int4 NOT NULL,
	card_color_id int4 NOT NULL,
	text_font_id int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	border_color_id int4 NULL,
	font_color_id int4 NULL,
	ido_id int8 NULL,
	CONSTRAINT appearance_cards_pkey PRIMARY KEY (id),
	CONSTRAINT fk_appearance_cards_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id),
	CONSTRAINT fk_border_color_id FOREIGN KEY (border_color_id) REFERENCES public.color(id),
	CONSTRAINT fk_card_color_id FOREIGN KEY (card_color_id) REFERENCES public.color(id),
	CONSTRAINT fk_format_card_id FOREIGN KEY (format_card_id) REFERENCES public.button_shape(id),
	CONSTRAINT fk_font_color_id FOREIGN KEY (font_color_id) REFERENCES public.color(id),
	CONSTRAINT fk_text_font_id FOREIGN KEY (text_font_id) REFERENCES public.text_font(id)
	);