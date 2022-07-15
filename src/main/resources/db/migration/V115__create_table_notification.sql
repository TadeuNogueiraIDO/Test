CREATE TABLE public.notification (
    id bigserial NOT NULL,
    "read" bool NOT null DEFAULT false,
    notification_type varchar(255) NOT NULL,
    ido_id int8 NOT NULL,
    user_id int8 NOT NULL,
    dt_created timestamp NOT NULL,
    dt_deleted timestamp NULL,
    dt_updated timestamp NULL,
    "version" int4 NOT NULL,
    CONSTRAINT notification_pk PRIMARY KEY (id),
    CONSTRAINT fk_notification_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id),
    CONSTRAINT fk_notification_user FOREIGN KEY (user_id) REFERENCES public.user(id)
);