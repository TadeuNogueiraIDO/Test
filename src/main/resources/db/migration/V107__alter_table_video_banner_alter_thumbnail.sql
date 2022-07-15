ALTER TABLE public.video_banner DROP COLUMN thumbnail;
ALTER TABLE public.video_banner ADD COLUMN thumbnail varchar(250);