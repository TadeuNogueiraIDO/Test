ALTER TABLE public.type_notification RENAME COLUMN description TO message;
ALTER TABLE public.type_notification ADD icon int8 NULL;
ALTER TABLE public.notification DROP CONSTRAINT notification_ido_fk;
ALTER TABLE public.notification DROP CONSTRAINT notification_invoice_fk;



ALTER TABLE public.notification DROP COLUMN value;
ALTER TABLE public.notification DROP COLUMN month_type_invoice;
ALTER TABLE public.notification DROP COLUMN date_type_request;
ALTER TABLE public.notification DROP COLUMN title;
ALTER TABLE public.notification DROP COLUMN icon;
ALTER TABLE public.notification DROP COLUMN id_invoice;
ALTER TABLE public.notification DROP COLUMN ido;
ALTER TABLE public.notification DROP COLUMN message;

ALTER TABLE public.notification ADD id_pdf int8 NULL;
ALTER TABLE public.notification ADD id_contact_us int8 NULL;
ALTER TABLE public.notification ADD id_wallet int8 NULL;
ALTER TABLE public.notification ADD id_single_quick_pay int8 NULL;
ALTER TABLE public.notification ADD id_shop_quick_pay int8 NULL;