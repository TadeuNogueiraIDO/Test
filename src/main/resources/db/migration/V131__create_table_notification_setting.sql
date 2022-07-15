CREATE TABLE public.notification_setting (
    id bigserial NOT NULL,
    active_notifications bool NOT null,
    system_warnings bool not null,
    requests bool not null,
    wallet bool not null,
    form bool not null,
    commissions bool not null,
    user_id int8 NOT NULL,
    dt_created timestamp NOT NULL,
    dt_deleted timestamp NULL,
    dt_updated timestamp NULL,
    "version" int4 NOT NULL,
    CONSTRAINT notification_setting_pk PRIMARY KEY (id),
    CONSTRAINT fk_notification_user FOREIGN KEY (user_id) REFERENCES public.user(id)
);
