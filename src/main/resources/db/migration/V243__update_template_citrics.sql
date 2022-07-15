INSERT INTO public.wallpaper_color_gradient
(id, "name", alignment_start, alignment_end)
VALUES(29,'Gradiente 27 ', 'TOPCENTER', 'BOTTOMCENTER');

INSERT INTO public.wallpaper_color_gradient_step
(wallpaper_color_gradient_id, color)
VALUES( 29 , 'FEA800');

INSERT INTO public.wallpaper_color_gradient_step
(wallpaper_color_gradient_id, color)
VALUES( 29 , 'AFCB37');

update pre_def_model_param set value = '29' where id = 16;


