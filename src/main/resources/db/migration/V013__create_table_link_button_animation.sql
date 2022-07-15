CREATE TABLE public.link_button_animation (
	link_id int8 NOT NULL,
	button_animation_id int8 NOT NULL,
	CONSTRAINT fk5etqb7jdesr9f0sugr5e66okx FOREIGN KEY (link_id) REFERENCES public.link(id),
	CONSTRAINT fkr8x6fntihln702wt2klf424dy FOREIGN KEY (button_animation_id) REFERENCES public.button_animation(id)
);