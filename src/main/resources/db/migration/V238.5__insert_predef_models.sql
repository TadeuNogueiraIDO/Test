/*DADOS AINDA NAO ORIGINAIS. Quando estivermos os modelos corretamentes, eh necessario transformar esse arquivo em migration*/

/*reflexo*/
insert into pre_def_model (id, name, file_id,showcase, dt_created, version) values (1, 'REFLECTION', 905, false, '2022-01-20 18:08:52.889',0);
insert into pre_def_model (id, name, file_id,showcase,dt_created, version) values (2, 'REFLECTION_VITRINE', 906, true, '2022-01-20 18:08:52.889',0);

insert into model_param (id, name, type,dt_created, version) values (1, 'GALLERY', 'Long', '2022-01-20 18:08:52.889', 0);

insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (1, 1, 1, 43, '2022-01-20 18:08:52.889', 0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (2, 1, 2, 43, '2022-01-20 18:08:52.889', 0);

/*fundo branco*/
insert into pre_def_model (id, name, file_id,showcase, dt_created, version) values (3, 'WHITE', 907, false, '2022-01-20 18:08:52.889',0);
insert into pre_def_model (id, name, file_id,showcase,dt_created, version) values (4, 'WHITE_VITRINE', 908, true, '2022-01-20 18:08:52.889',0);

insert into model_param (id, name, type,dt_created, version) values (2, 'COLOR', 'Long', '2022-01-20 18:08:52.889', 0);

insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (3, 2, 3, 36, '2022-01-20 18:08:52.889', 0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (4, 2, 4, 36, '2022-01-20 18:08:52.889', 0);


/*fundo marine*/
insert into pre_def_model (id, name, file_id,showcase, dt_created, version) values (5, 'MARINE', 909, false, '2022-01-20 18:08:52.889',0);
insert into pre_def_model (id, name, file_id,showcase,dt_created, version) values (6, 'MARINE_VITRINE', 910, true, '2022-01-20 18:08:52.889',0);

insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (5, 2, 5, 43, '2022-01-20 18:08:52.889', 0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (6, 2, 6, 43, '2022-01-20 18:08:52.889', 0);

/*fundo citrico*/
insert into pre_def_model (id, name, file_id,showcase, dt_created, version) values (7, 'CITRIC', 912, false, '2022-01-20 18:08:52.889',0);
insert into pre_def_model (id, name, file_id,showcase,dt_created, version) values (8, 'CITRIC_VITRINE', 913, true, '2022-01-20 18:08:52.889',0);

insert into model_param (id, name, type,dt_created, version) values (3, 'GRADIENT', 'Long', '2022-01-20 18:08:52.889', 0);


insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (7, 3, 7, 2, '2022-01-20 18:08:52.889', 0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (8, 3, 8, 2, '2022-01-20 18:08:52.889', 0);





