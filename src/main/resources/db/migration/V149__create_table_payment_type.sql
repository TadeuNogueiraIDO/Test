CREATE TABLE public.payment_type (
	id bigserial NOT NULL,
	file_uuid varchar(100),
	name varchar(50),
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp,
	version int4 NOT NULL,
	CONSTRAINT payment_type_pkey PRIMARY KEY(id)
);

                                                    
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('1', '3e3b020b-124e-4eec-b965-920691e1e3f1', 'Dinheiro','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('2', '8ee6fff5-417c-4dc7-9b36-f8ea8583bdfb', 'Pix','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('3', 'e0ab18b2-25f4-49ca-9fe6-315d6828ff5a', 'Cartão de débito Visa','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('4', 'e0ab18b2-25f4-49ca-9fe6-315d6828ff5a', 'Cartão de crédito Visa','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('5', 'b1f0ba13-3551-49c6-a7ae-3a42ebc559d3', 'Cartão de débito Mastercard','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('6', 'b1f0ba13-3551-49c6-a7ae-3a42ebc559d3', 'Cartão de crédito Mastercard','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('7', 'c0ae7e2d-c0e8-4d25-8d56-8499a705fdb3', 'Cartão de débito Elo','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('8', 'c0ae7e2d-c0e8-4d25-8d56-8499a705fdb3', 'Cartão de crédito Elo','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('9', '5c7e66a4-c496-4017-941e-723f9382b168', 'Cartão de crédito Hipercard','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('10', '6913f63e-9762-496e-8622-7e9da01fcd42', 'Cartão de crédito Diners Club','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('11', 'a1338b17-927a-4b80-bd4e-a351f2796792', 'Cartão de crédito American Express','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('12', 'd453ea4f-ff87-4582-ac91-6887bfc48316', 'Alelo Refeição','2022-03-30 13:33:13.678',0);
INSERT INTO payment_type (id,file_uuid, "name",dt_created,version) VALUES('13', '07930aa8-235a-4d44-bac9-611250b0f9d6', 'Outra','2022-03-30 13:33:13.678',0);
                                         