ALTER TABLE public.wallpaper_color_gradient_step DROP COLUMN alignment_start;
ALTER TABLE public.wallpaper_color_gradient_step DROP COLUMN alignment_end;
ALTER TABLE public.wallpaper_color_gradient ADD alignment_start varchar NULL;
ALTER TABLE public.wallpaper_color_gradient ADD alignment_end varchar NULL;
