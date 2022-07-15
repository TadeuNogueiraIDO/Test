ALTER TABLE shop_quick_pay ADD COLUMN type_shipping varchar(20);
ALTER TABLE shop_quick_pay ADD COLUMN shipping_description varchar(250);

ALTER TABLE single_quick_pay ADD COLUMN type_shipping varchar(20);
ALTER TABLE single_quick_pay ADD COLUMN shipping_description varchar(250);