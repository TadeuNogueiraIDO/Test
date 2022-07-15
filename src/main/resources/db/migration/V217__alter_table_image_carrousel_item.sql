ALTER TABLE public.image_carousel_item DROP COLUMN dt_updated;
ALTER TABLE public.image_carousel_item DROP COLUMN dt_deleted;
ALTER TABLE public.image_carousel_item DROP COLUMN dt_created;
ALTER TABLE public.image_carousel_item DROP COLUMN "version";

ALTER TABLE public.image_carousel_item_detail DROP COLUMN dt_updated;
ALTER TABLE public.image_carousel_item_detail DROP COLUMN dt_deleted;
ALTER TABLE public.image_carousel_item_detail DROP COLUMN dt_created;
ALTER TABLE public.image_carousel_item_detail DROP COLUMN "version";
