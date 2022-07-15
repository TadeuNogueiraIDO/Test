ALTER TABLE public.shop ADD term_use text NULL DEFAULT null;
ALTER TABLE public.shop ADD term_reimbursement text NULL DEFAULT null;
ALTER TABLE public.shop ADD term_privacy text NULL DEFAULT null;
ALTER TABLE public.shop ADD terms_id int8 NULL DEFAULT 1;
ALTER TABLE public.shop ADD CONSTRAINT shop_terms_fk FOREIGN KEY (terms_id) REFERENCES public.policies_terms_use_shop(id);

