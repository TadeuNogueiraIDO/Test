CREATE TABLE public.pre_def_model_param (
	id bigserial NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,
	version int4 NOT NULL,
	model_param_id int8 NULL,
	pre_def_model_id int8 NULL,
	CONSTRAINT pre_def_model_param_pkey PRIMARY KEY (id),
	CONSTRAINT fk5xmwnb37rpq2g1wgr1mqte4c7 FOREIGN KEY (pre_def_model_id) REFERENCES public.pre_def_model(id),
	CONSTRAINT fk8xtwo0wq5yusqf9pifeh4cv0p FOREIGN KEY (model_param_id) REFERENCES public.model_param(id)
);