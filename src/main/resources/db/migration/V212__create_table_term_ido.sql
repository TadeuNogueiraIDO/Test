CREATE TABLE public.policies_terms_use_shop (
	id bigserial NOT NULL,
	term_privacy text NULL DEFAULT null,
	term_reimbursement text NULL DEFAULT null,
	term_use text NULL DEFAULT null,
	CONSTRAINT terms_ido_pk PRIMARY KEY (id)
);

INSERT INTO public.policies_terms_use_shop (id, term_privacy, term_reimbursement, term_use) VALUES(1, 'https://www.iubenda.com/privacy-policy/69773181', 'https://idolink.bio/', 'https://idolink.bio/sandbox/demodig');

