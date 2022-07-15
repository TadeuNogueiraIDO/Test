ALTER TABLE delivery_payment_setup 
DROP COLUMN money,
DROP COLUMN pix,
DROP COLUMN debit_card_visa,
DROP COLUMN credit_card_visa,
DROP COLUMN debit_card_mastercard,
DROP COLUMN credit_card_mastercard,
DROP COLUMN debit_card_elo,
DROP COLUMN credit_card_elo,
DROP COLUMN credit_card_hipercard,
DROP COLUMN credit_card_diners_club,
DROP COLUMN credit_card_american_express,
DROP COLUMN alelo_meal,
DROP COLUMN other;

ALTER TABLE delivery_payment_setup ADD COLUMN payment_type_id int8;
ALTER TABLE delivery_payment_setup ADD CONSTRAINT fk_delivery_payment_setup_payment_type FOREIGN KEY (payment_type_id) REFERENCES public.payment_type(id);



