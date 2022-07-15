ALTER TABLE payment_setup DROP COLUMN gateway_ido,
DROP COLUMN payment_delivery,
DROP COLUMN mercado_pago,
DROP COLUMN pag_seguro,
DROP COLUMN paypal,
DROP COLUMN discount_coupon,
DROP COLUMN fee_or_tax,
DROP COLUMN tip_checkout;

ALTER TABLE payment_setup ADD COLUMN type_payment_setup varchar(200);
