CREATE TABLE public.currency (
	id bigserial NOT NULL,
	simbol varchar(255) NULL,
	CONSTRAINT currency_pk PRIMARY KEY (id)
);

INSERT into currency (simbol) values
('R$'),
('$');