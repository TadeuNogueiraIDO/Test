CREATE TABLE public.access_report (
    id bigserial NOT NULL,
    ip varchar(40) NOT null,
    tool_cod_name varchar(50),
    ido_id int8 NOT NULL,
    generic_tool_id int8,
    access_date timestamp NOT NULL,
    CONSTRAINT access_report_pk PRIMARY KEY (id),
    CONSTRAINT fk_access_report_ido FOREIGN KEY (ido_id) REFERENCES public.ido(id)
);
