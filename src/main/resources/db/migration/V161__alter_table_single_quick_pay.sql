ALTER TABLE public.single_quick_pay DROP COLUMN ido_id;
ALTER TABLE public.single_quick_pay ADD COLUMN user_id int8;
ALTER TABLE public.single_quick_pay add CONSTRAINT single_quick_pay_user_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);