CREATE TABLE public.ido_model_param (
	ido_id int8 NOT NULL,
	model_param_id int8 NOT NULL,
	CONSTRAINT fkb1hlba6l5eie70pbptce1knp1 FOREIGN KEY (model_param_id) REFERENCES public.model_param(id),
	CONSTRAINT fkgg451agiipexgcsryhir42eaq FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);