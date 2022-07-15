CREATE TABLE public.group (
	id bigserial NOT NULL,
	name varchar NOT NULL,
	CONSTRAINT group_pk PRIMARY KEY (id)
);

CREATE TABLE public.permission (
	id bigserial NOT NULL,
	name varchar NOT NULL,
	CONSTRAINT permisssion_pk PRIMARY KEY (id)
);

CREATE TABLE public.group_permission (
	group_id bigint NOT NULL,
	permission_id bigint NOT NULL,
	CONSTRAINT fk_group_permission_permission FOREIGN KEY (permission_id) REFERENCES public.permission(id),
	CONSTRAINT fk_group_permission_group FOREIGN KEY (group_id) REFERENCES public.group(id)
);

CREATE TABLE public.user_group (
	user_id bigint NOT NULL,
	group_id bigint NOT NULL,
	CONSTRAINT fk_user_group_user FOREIGN KEY (user_id) REFERENCES public.user(id),
	CONSTRAINT fk_user_group_group FOREIGN KEY (group_id) REFERENCES public.group(id)
);
