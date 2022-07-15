CREATE TABLE public.activity (
	id bigserial NOT NULL,
	type_activity varchar(255) NOT NULL,
	segment varchar(255) NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	"version" int4 NOT NULL,
	CONSTRAINT activity_pk PRIMARY KEY (id)
);


insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Alimentos e bebidas');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Agro');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Animais');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Bebês');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Beleza e cuidado pessoal');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Cama, mesa e banho');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Brinquedos e Hobbies');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Casa e jardim');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Celulares e telefone');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Construção');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Delivery e alimentos');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Eletrodomésticos');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Eletrônicos');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Games');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Informática');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Ingressos');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Instrumentos musicais');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Mercado');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Moda e vestuário');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Saúde');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'PRODUCT', 'Outros');

insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Academia e Esportes');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Advogados');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Animais');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Consultoria');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Contabilidade');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Gastronomia');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Gráficas e Impressão');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Educação');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Festa e Eventos');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Marketing e Internet');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Odontologia');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Profissional liberal');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Saúde');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Suporte técnico');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Viagens e Turismo');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Veículos e Transporte');
insert into activity (dt_created, dt_deleted, dt_updated, "version", type_activity, segment) values ('2022-01-20 17:48:10.241', NULl, '2022-01-20 17:48:10.241', 0, 'SERVICE', 'Outros');







