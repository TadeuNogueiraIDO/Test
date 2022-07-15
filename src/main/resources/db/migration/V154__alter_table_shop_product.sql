ALTER TABLE shop_product DROP COLUMN type_product;
alter table shop_product add column product_type_id int8 null;
alter table shop_product add CONSTRAINT fk_product_type_shop_product FOREIGN KEY (product_type_id) REFERENCES public.product_type(id);