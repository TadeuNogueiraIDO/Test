ALTER TABLE public.product_variation ALTER COLUMN size type varchar(30);
ALTER TABLE public.product_variation ADD COLUMN shop_product_id int8;
ALTER TABLE public.product_variation ADD CONSTRAINT product_variation_product_fk FOREIGN KEY (shop_product_id) REFERENCES public.shop_product(id);

ALTER TABLE public.shop_product DROP COLUMN is_digital;
ALTER TABLE public.shop_product DROP COLUMN digital_url;
ALTER TABLE public.shop_product DROP COLUMN digital_orientation;
ALTER TABLE public.shop_product ADD COLUMN type_product varchar(30);

ALTER TABLE public.product_variation ADD COLUMN promo_price float8;
ALTER TABLE public.product_variation ADD COLUMN digital_url varchar(250);
ALTER TABLE public.product_variation ADD COLUMN digital_orientation varchar(250);
ALTER TABLE public.product_variation ADD COLUMN length float8;
ALTER TABLE public.product_variation ADD COLUMN width float8;
ALTER TABLE public.product_variation ADD COLUMN height float8; 
ALTER TABLE public.product_variation ADD COLUMN unit_of_weight varchar(30);

