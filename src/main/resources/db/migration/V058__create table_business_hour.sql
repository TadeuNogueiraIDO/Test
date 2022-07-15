CREATE TABLE public.business_hour (
	id bigserial NOT NULL,
	title varchar(255) NOT NULL,
	type int4 NULL, 
	ido_id bigint NOT NULL,
    version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,	
	CONSTRAINT business_hour_pkey PRIMARY KEY (id)
);
CREATE TABLE public.business_hour_day (
	id bigserial NOT NULL,
	day int4 NOT NULL,
	enabled boolean NOT NULL,
    business_hour_id bigint NOT NULL,
    version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,	
	CONSTRAINT business_hour_day_pkey PRIMARY KEY (id),
    CONSTRAINT fk_business_hour_day_business_hour FOREIGN KEY (business_hour_id) REFERENCES business_hour(id)
);
CREATE TABLE public.business_hour_schedule (
	id bigserial NOT NULL,
	open_time time NOT NULL,
	close_time time NOT NULL,
    version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,	
	CONSTRAINT business_hour_schedule_pkey PRIMARY KEY (id)
);
CREATE TABLE public.business_hour_alternative_schedule (
	id bigserial NOT NULL,
	day date NOT NULL,
    business_hour_id bigint NOT NULL,
    version int4 NOT NULL,  
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NOT NULL,	
	CONSTRAINT business_hour_extra_pkey PRIMARY KEY (id),
    CONSTRAINT fk_business_hour_extra_business_hour FOREIGN KEY (business_hour_id) REFERENCES business_hour(id)
);
CREATE TABLE public.business_hour_day_schedule (
	business_hour_day_id bigint NOT NULL,
	business_hour_schedule_id bigint NOT NULL,
    CONSTRAINT fk_business_hour_day_id_business_hour_day FOREIGN KEY (business_hour_day_id) REFERENCES business_hour_day(id),
    CONSTRAINT fk_business_hour_schedule_id_business_hour_schedule FOREIGN KEY (business_hour_schedule_id) REFERENCES business_hour_schedule(id)
);
CREATE TABLE public.business_hour_alternative_schedule_schedule (
	business_hour_alternative_schedule_id bigint NOT NULL,
	business_hour_schedule_id bigint NOT NULL,
    CONSTRAINT fk_business_hour_alternative_schedule_id_business_hour_alternative_schedule FOREIGN KEY (business_hour_alternative_schedule_id) REFERENCES business_hour_alternative_schedule(id),
    CONSTRAINT fk_business_hour_schedule_id_business_hour_schedule FOREIGN KEY (business_hour_schedule_id) REFERENCES business_hour_schedule(id)
);