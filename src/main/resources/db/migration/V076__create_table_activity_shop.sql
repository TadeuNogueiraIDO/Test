CREATE TABLE public.activity_shop (
	id bigserial NOT NULL,
	activity_id int8 NULL,
	presentation_shop_id int8 NULL,
	other_segment varchar(255) NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT activity_shop_pk PRIMARY KEY (id),
	CONSTRAINT fk_activity FOREIGN KEY (activity_id) REFERENCES public.activity(id),
	CONSTRAINT fk_presentation_shop FOREIGN KEY (presentation_shop_id) REFERENCES public.presentation_shop(id)
);
