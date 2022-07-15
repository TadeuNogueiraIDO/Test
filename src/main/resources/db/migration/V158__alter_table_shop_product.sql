ALTER TABLE product_variation 
ALTER COLUMN price TYPE decimal(10,2),
ALTER COLUMN promo_price TYPE decimal(10,2);

ALTER TABLE product_variation ADD COLUMN sku varchar(150);

ALTER TABLE product_variation DROP COLUMN product_color,
DROP COLUMN size;

ALTER TABLE product_variation ADD COLUMN filter_type varchar(50);
ALTER TABLE product_variation ADD COLUMN filter_value varchar(50);

