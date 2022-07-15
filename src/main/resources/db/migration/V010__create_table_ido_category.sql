CREATE TABLE public.ido_category (
	ido_id int8 NOT NULL,
	category_id int8 NOT NULL,
	CONSTRAINT fk4o1ilsqtl9gu7012rp9q90mk FOREIGN KEY (category_id) REFERENCES public.category(id),
	CONSTRAINT fkgr39klrn9odeu675v08e72o5e FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);