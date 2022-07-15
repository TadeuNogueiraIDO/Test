CREATE TABLE public.button_shape (
	id bigserial NOT NULL,
	name varchar(255) NULL,
	CONSTRAINT button_shape_pk PRIMARY KEY (id)
);

INSERT into button_shape (name) values
('Square'),
('Rounded'),
('Round');