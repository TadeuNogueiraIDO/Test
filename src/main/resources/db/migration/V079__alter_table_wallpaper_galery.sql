ALTER TABLE public.wallpaper_gallery RENAME COLUMN file_id TO file_uuid;
ALTER TABLE public.wallpaper_gallery ALTER COLUMN file_uuid TYPE varchar USING file_uuid::varchar;

ALTER TABLE public.wallpaper_gallery RENAME COLUMN thumbnail_id TO thumbnail_uuid;
ALTER TABLE public.wallpaper_gallery ALTER COLUMN thumbnail_uuid TYPE varchar USING file_uuid::varchar;
