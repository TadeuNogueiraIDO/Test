/*Nova versao de wallpaper*/

ALTER TABLE pre_def_model ADD COLUMN type VARCHAR(20);
ALTER TABLE pre_def_model ADD COLUMN file_uuid VARCHAR(100);

ALTER TABLE public.pre_def_model ALTER COLUMN file_id DROP NOT NULL;


/*#######LINK#################################*/

/*####GALERY####*/

/*SCRIBBLE*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (9, 'SCRIBBLE', '144c4d08-3971-4cc6-85ab-a179e6d5e26b','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (9, 1, 9, '83d19366-b6fc-4645-8447-f4f3646b4ddb', '2022-06-21 18:08:52.889', 0);

/*SUMMER*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (10, 'SUMMER', 'c459edcf-22fb-4e6a-bc7a-e2339a1abc0e','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (10, 1, 10, '4501ab36-0999-4166-84f3-ecb159f94d70', '2022-06-21 18:08:52.889', 0);

/*NEW REFLECTION*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (11, 'NEWREFLECTION', '91af3123-87a7-4391-bf75-8ca4ee6a79d8','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (11, 1, 11, 'e1414f15-1c84-412c-a163-0f2e97930295', '2022-06-21 18:08:52.889', 0);

/*STELLAR*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (12, 'STELLAR', '26311ec5-2cc7-4e00-91c2-7421f1665f6e','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (12, 1, 12, 'bf6bc282-9f2b-4c38-a187-a46bcea0e828', '2022-06-21 18:08:52.889', 0);

/*####COR SOLIDA####*/

/*NEW WHITE*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (13, 'NEWWHITE', 'bd0755a8-1d84-4bcb-a6ef-4d94c1ebf8bc','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (13, 2, 13, 36, '2022-06-21 18:08:52.889', 0);

/*NEW MARINE*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (14, 'NEWMARINE', '26f6063d-af60-40b5-b4fa-9633eabac203','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (14, 2, 14, 43, '2022-06-21 18:08:52.889', 0);

INSERT INTO public.wallpaper_color (id, name, color) VALUES(71, 'Color19', '0XFFF4F4F4');

/*MURAL*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (15, 'MURAL', 'a481d1f5-5d0a-4286-97b2-0822b4584910','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (15, 2, 15, 71, '2022-06-21 18:08:52.889', 0);

/*#### GRADIENT ####*/

/*NEW CITRIC*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (16, 'NEWCITRIC', '41102fb9-d3bd-4a11-8b9e-f3bcfd99d61d','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (16, 3, 16, 2, '2022-06-21 18:08:52.889', 0);

/*AURORA*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (17, 'AURORA', '7d1dafa2-e903-4068-a967-0629e0f73e98','LINK', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (17, 3, 17, 23, '2022-06-21 18:08:52.889', 0);


/*########PAGINA######################*/

/*###GALLERY###*/


/*Minha Musica*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (18, 'MYMUSIC', '59ec6e7e-1d26-4f0e-959c-89cc23f5d3b6','PAGE', '2022-06-23 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (18, 1, 18, '1e26ce68-6acd-492c-9c4c-675fda745b9a', '2022-06-23 18:08:52.889', 0);

/*Rainbow*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (19, 'RAINBOW', '60b9659f-a4f6-47c3-a135-5bc45f3e2348','PAGE', '2022-06-23 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (19, 1, 19, '3ab3e730-6e86-45ba-86e7-61b1561d9d1a', '2022-06-23 18:08:52.889', 0);

/*Urban*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (20, 'URBAN', 'b60fff47-4196-4526-8ff9-7e8181b125a7','PAGE', '2022-06-23 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (20, 1, 20, 'b904f534-9f4e-4860-afef-a7df5295c078', '2022-06-23 18:08:52.889', 0);

/*Portfolio*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (21, 'PORTFOLIO', 'c8d03f48-a8d0-422d-b857-34ccefe3ac4c','PAGE', '2022-06-23 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (21, 1, 21, '374e42e6-722e-4752-b154-c4bd19dbed9a', '2022-06-23 18:08:52.889', 0);

/*Linha e Agulha*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (22, 'LINENEEDLE', '9964a363-dbd9-4928-ba6c-f91d9ae19261','PAGE', '2022-06-23 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (22, 1, 22, '4fedf5d6-6a57-4307-8eab-1668be88d2df', '2022-06-23 18:08:52.889', 0);

/*####COR SOLIDA####*/

/*HEALTH*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (23, 'HEALTH', '72349f15-2985-4353-86ef-7de1d1fe0e2a','PAGE', '2022-06-23 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (23, 2, 23, 36, '2022-06-21 18:08:52.889', 0);

/*INFLUENCER*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (24, 'INFLUENCER', '32b55361-4a1d-4b14-b73e-f5158031ddd4','PAGE', '2022-06-23 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (24, 2, 24, 36, '2022-06-21 18:08:52.889', 0);

/*#### GRADIENT ####*/

/*LOLLIPOP*/
insert into pre_def_model (id, name, file_uuid, type, dt_created, version) values (25, 'LOLLIPOP', '41102fb9-d3bd-4a11-8b9e-f3bcfd99d61d','PAGE', '2022-06-21 18:08:52.889',0);
insert into pre_def_model_param (id, model_param_id, pre_def_model_id, value, dt_created, version) values (25, 3, 25, 19, '2022-06-21 18:08:52.889', 0);



