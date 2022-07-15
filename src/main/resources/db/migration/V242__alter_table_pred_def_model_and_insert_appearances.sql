/*add columns appearances*/

ALTER TABLE pre_def_model ADD COLUMN appearance_button_id int8;
ALTER TABLE pre_def_model ADD COLUMN appearance_cards_id int8;
ALTER TABLE pre_def_model ADD COLUMN appearance_text_id int8;

ALTER TABLE pre_def_model ADD CONSTRAINT predefined_appearance_button_fk FOREIGN KEY (appearance_button_id) REFERENCES appearance_button(id);
ALTER TABLE pre_def_model ADD CONSTRAINT predefined_appearance_cards_fk FOREIGN KEY (appearance_cards_id) REFERENCES appearance_cards(id);
ALTER TABLE pre_def_model ADD CONSTRAINT appearance_text_fk FOREIGN KEY (appearance_text_id) REFERENCES appearance_text(id);

/*#######LINK#################################*/

/*####GALERY####*/

/*SCRIBBLE*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(2, 8, '2022-06-21 18:08:52.889', 0, '0XFFF4F4F4', '0XFFF4F4F4', '0XFF4D3D34');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(2, 8,'2022-06-21 18:08:52.889', 0, '0XFFF4F4F4', '0XFFF4F4F4', '0XFF4D3D34');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(8, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF4D3D34');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)
where "name" = 'SCRIBBLE';

/*SUMMER*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(2, 4, '2022-06-21 18:08:52.889', 0, '0XFFFFF3ED', '0XFFFFF3ED', '0XFF000000');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(2, 4,'2022-06-21 18:08:52.889', 0, '0XFFFFF3ED', '0XFFFFF3ED', '0XFF000000');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(4, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF000000');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'SUMMER';

/*NEW REFLECTION*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(3, 12, '2022-06-21 18:08:52.889', 0, '0XFF3C4D5F', '0XFF3C4D5F', '0XFFF6E9E2');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(3, 12,'2022-06-21 18:08:52.889', 0, '0XFF3C4D5F', '0XFF3C4D5F', '0XFFF6E9E2');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(12, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFFFFFFFF');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'NEWREFLECTION';

/*STELLAR*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(1, 8, '2022-06-21 18:08:52.889', 0, '0XFF3C4D5F', '0XFF3C4D5F', '0XFFF6E9E2');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(1, 8,'2022-06-21 18:08:52.889', 0, '0XFF3C4D5F', '0XFF3C4D5F', '0XFFF6E9E2');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(8, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF000000');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'STELLAR';

/*####COR SOLIDA####*/

/*NEW WHITE*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(1, 1, '2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0XFFFFFFFF', '0XFF000000');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(1, 1,'2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0XFFFFFFFF', '0XFF000000');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(1, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF000000');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'NEWWHITE';

/*NEW MARINE*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(1, 11, '2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0XFFFFFFFF', '0XFF303030');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(1, 11,'2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0XFFFFFFFF', '0XFF303030');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(11, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFFFFFFFF');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'NEWMARINE';

/*MURAL*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(3, 8, '2022-06-21 18:08:52.889', 0, '0XFFD4AEAD', '0XFFFFFFFF', '0XFF303030');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(3, 8,'2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0XFFD4AEAD', '0XFF303030');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(8, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF303030');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'MURAL';

/*#### GRADIENT ####*/

/*NEW CITRIC*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(1, 4, '2022-06-21 18:08:52.889', 0, '0XFFF4F4F4', '0XFFFFA800', '0XFF303030');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(1, 4,'2022-06-21 18:08:52.889', 0, '0XFFFFA800', '0XFFF4F4F4', '0XFF303030');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(1, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF000000');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'NEWCITRIC';

/*AURORA*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(3, 5, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFFFFFFFF', '0XFFFFFFFF');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(3, 5,'2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0X00000000', '0XFFFFFFFF');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(5, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFFFFFFFF');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'AURORA';


/*########PAGINA######################*/

/*###GALLERY###*/


/*Minha Musica*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(2, 6, '2022-06-21 18:08:52.889', 0, '0XFF171717', '0XFF171717', '0XFFFFFFFF');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(2, 6,'2022-06-21 18:08:52.889', 0, '0XFF171717', '0XFF171717', '0XFFFFFFFF');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(6, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF171717');


update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'MYMUSIC';


/*Rainbow*/


INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(3, 11, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFFFFFFFF', '0XFFFFFFFF');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(3, 11,'2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0X00000000', '0XFFFFFFFF');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(11, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFFFFFFFF');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'RAINBOW';

/*Urban*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(1, 4, '2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0XFFFFFFFF', '0XFF303030');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(1, 4,'2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0XFFFFFFFF', '0XFF303030');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(4, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF303030');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'URBAN';


/*Portfolio*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(3, 1, '2022-06-21 18:08:52.889', 0, '0XFF000000', '0XFF000000', '0XFFFFFFFF');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(3, 1,'2022-06-21 18:08:52.889', 0, '0XFF000000', '0XFF000000', '0XFFFFFFFF');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(1, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF000000');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'PORTFOLIO';

/*Linha e Agulha*/
INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(2, 13, '2022-06-21 18:08:52.889', 0, '0XFFF1EDE9', '0XFFF1EDE9', '0XFF4D3D34');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(2, 13,'2022-06-21 18:08:52.889', 0, '0XFFF1EDE9', '0XFFF1EDE9', '0XFF4D3D34');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(13, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF4D3D34');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'LINENEEDLE';

/*####COR SOLIDA####*/

/*HEALTH*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(2, 4, '2022-06-21 18:08:52.889', 0, '0XFFC6DDF3', '0XFFC6DDF3', '0XFF183B68');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(2, 4,'2022-06-21 18:08:52.889', 0, '0XFFC6DDF3', '0XFFC6DDF3', '0XFF183B68');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(4, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF183B68');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'HEALTH';

/*INFLUENCER*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(2, 8, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF66BFC5', '0XFF303030');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(2, 8,'2022-06-21 18:08:52.889', 0, '0XFF66BFC5', '0X00000000', '0XFF303030');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(8, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF303030');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'INFLUENCER';

/*#### GRADIENT ####*/

/*LOLLIPOP*/

INSERT INTO public.appearance_button
(button_shape_id, text_font_id, dt_created, "version", button_color, border_color, font_color)
VALUES(3, 5, '2022-06-21 18:08:52.889', 0, '0XFFE4BCD1', '0XFFFFFFFF', '0XFF000000');

INSERT INTO public.appearance_cards
(format_card_id, text_font_id, dt_created, "version", border_color, card_color, font_color)
VALUES(3, 5,'2022-06-21 18:08:52.889', 0, '0XFFFFFFFF', '0XFFE4BCD1', '0XFF000000');

INSERT INTO public.appearance_text
(text_font_id,  dt_created, "version", background_color, font_color)
VALUES(5, '2022-06-21 18:08:52.889', 0, '0X00000000', '0XFF93436C');

update public.pre_def_model set appearance_button_id = (SELECT max(id) from public.appearance_button),
appearance_cards_id = (SELECT max(id) from public.appearance_cards),
appearance_text_id = (SELECT max(id) from public.appearance_text)

where "name" = 'LOLLIPOP';

