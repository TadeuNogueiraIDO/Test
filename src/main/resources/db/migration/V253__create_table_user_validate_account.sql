CREATE TABLE public.user_validate_account (

    id bigserial NOT NULL,
    user_id int8 NOT NULL,
    dt_expiration timestamp NULL,
    used bool NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY (id)

);