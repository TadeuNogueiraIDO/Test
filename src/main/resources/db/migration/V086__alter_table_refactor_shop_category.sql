ALTER TABLE public.shop ADD COLUMN activity_shop_id int8;
ALTER TABLE public.shop ADD CONSTRAINT shop_activity_shop_fk FOREIGN KEY (activity_shop_id) REFERENCES public.activity_shop(id);

ALTER TABLE public.activity_shop DROP COLUMN shop_id;

ALTER TABLE public.shop_category DROP COLUMN ido_id;
ALTER TABLE public.shop_category ADD COLUMN shop_id int8;
ALTER TABLE public.shop_category ADD CONSTRAINT shop_category_shop_fk FOREIGN KEY (shop_id) REFERENCES public.shop(id);
