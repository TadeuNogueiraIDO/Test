CREATE TABLE public.delivery_payment_setup(
    id bigserial not NULL,
    other_payment varchar NULL,
    "money" boolean NULL,
    pix boolean NULL,
    debit_card_visa boolean NULL,
    credit_card_visa boolean NULL,
    debit_card_mastercard boolean NULL,
    credit_card_mastercard boolean NULL,
    debit_card_elo boolean NULL,
    credit_card_elo boolean NULL,
    credit_card_hipercard boolean NULL,
    credit_card_diners_club boolean NULL,
    credit_card_american_express boolean NULL,
    alelo_meal boolean NULL,
    other boolean NULL,
    shop_id int8 not null,
    dt_created timestamp not null,
    dt_deleted timestamp null,
    dt_updated timestamp null,
    "version" int4 not null,
    CONSTRAINT delivery_payment_setup_pk PRIMARY KEY (id),
    CONSTRAINT delivery_payment_setup_shop_fk foreign key (shop_id) references shop(id)
);