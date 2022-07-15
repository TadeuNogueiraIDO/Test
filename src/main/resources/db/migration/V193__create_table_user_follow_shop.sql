CREATE TABLE public.user_follow_shop (
	id bigserial NOT NULL,
	user_id bigserial NOT NULL,
	shop_id bigserial NOT NULL,
	"version" int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	CONSTRAINT user_follow_shop_fk FOREIGN KEY (user_id) REFERENCES public."user"(id),
	CONSTRAINT user_follow_shop_fk_1 FOREIGN KEY (shop_id) REFERENCES public.shop(id)
);
