create table tool(
	id bigserial NOT NULL,
	name varchar NOT NULL,
	description varchar null,
	active boolean not null,
	reuse boolean not null,
	url varchar NOT NULL,
	dt_created timestamp null,
	dt_updated timestamp null,
	dt_deleted timestamp null,
	version bigint null,
	CONSTRAINT tool_pk PRIMARY KEY (id)
);
