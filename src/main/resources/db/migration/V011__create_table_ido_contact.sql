CREATE TABLE public.ido_contact (
	ido_id int8 NOT NULL,
	contact_id int8 NOT NULL,
	enable bool null,
	value int4 null,
	CONSTRAINT fk9vwxgfl4em8urha7unckd3s96 FOREIGN KEY (ido_id) REFERENCES public.ido(id),
	CONSTRAINT fkjs7uq7b56f1brqeceeh8ixhky FOREIGN KEY (contact_id) REFERENCES public.contact(id)
);