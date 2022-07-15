CREATE TABLE public.tool_currency (
	tool_id int8 NOT NULL,
	currency_id int8 NOT NULL,
	CONSTRAINT fk_tool_currency_id FOREIGN KEY (currency_id) REFERENCES public.currency(id),
	CONSTRAINT fk_tool_id FOREIGN KEY (tool_id) REFERENCES public.tool(id)
);

insert into tool_currency (tool_id, currency_id)
values (1 , 1), 
(1 , 2),
(2 , 1),
(2 , 2),
(3 , 1),
(3 , 2),
(4 , 1),
(4 , 2),
(5 , 1),
(5 , 2),
(6 , 1),
(6 , 2),
(7 , 1),
(7 , 2),
(8 , 1),
(8 , 2),
(9 , 1),
(9 , 2),
(11 , 1),
(11 , 2);