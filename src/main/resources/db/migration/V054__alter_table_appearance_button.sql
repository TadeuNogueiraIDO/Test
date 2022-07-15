ALTER TABLE appearance_button ADD column border_color_id int4 NULL, add column font_color_id int4 null, ADD column ido_id bigint NULL,
add CONSTRAINT fk_border_color_id FOREIGN KEY (border_color_id) REFERENCES public.color(id),
add CONSTRAINT fk_font_color_id FOREIGN KEY (font_color_id) REFERENCES public.color(id),
add CONSTRAINT fk_appearance_button_ido FOREIGN KEY (ido_id) references ido(id);