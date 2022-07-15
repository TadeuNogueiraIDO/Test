ALTER TABLE public.notification DROP COLUMN dt_updated;
ALTER TABLE public.notification DROP COLUMN dt_deleted;
ALTER TABLE public.notification DROP COLUMN dt_created;
ALTER TABLE public.notification DROP COLUMN "version";
ALTER TABLE public.notification DROP CONSTRAINT fk_notification_ido;
ALTER TABLE public.notification DROP COLUMN ido_id;



ALTER TABLE public.notification ADD value double precision NULL;
ALTER TABLE public.notification ADD month_type_invoice varchar NULL;
ALTER TABLE public.notification ADD date_type_request varchar NULL;
ALTER TABLE public.notification ADD title varchar NULL;
ALTER TABLE public.notification ADD icon int8 NULL;
ALTER TABLE public.notification ADD creation_date timestamp NULL;
ALTER TABLE public.notification ALTER COLUMN notification_type TYPE int8 USING notification_type::int8;
ALTER TABLE public.notification ADD CONSTRAINT notification_type_fk FOREIGN KEY (notification_type) REFERENCES public.type_notification(id);
ALTER TABLE public.notification ADD id_invoice int8 NULL;
ALTER TABLE public.notification ADD CONSTRAINT notification_invoice_fk FOREIGN KEY (id_invoice) REFERENCES public.invoice(id);
ALTER TABLE public.notification ADD ido int8 NOT NULL;
ALTER TABLE public.notification ADD CONSTRAINT notification_ido_fk FOREIGN KEY (ido) REFERENCES public.ido(id);
ALTER TABLE public.notification ADD message varchar NULL;
