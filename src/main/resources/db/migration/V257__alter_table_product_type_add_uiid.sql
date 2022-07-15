ALTER TABLE public.product_type drop column icon;
ALTER TABLE public.product_type add column file_uiid varchar(50);

UPDATE product_type SET file_uiid='3176fb93-8b44-4809-aaf8-dc7e90f7311a' WHERE type='PHYSICAL';
UPDATE product_type SET file_uiid='d94fc91b-39d6-4163-89f2-0d3d8fa35216' WHERE type='DIGITAL';