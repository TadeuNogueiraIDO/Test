ALTER TABLE ido_model_param ADD id bigint NOT NULL;
ALTER TABLE ido_model_param ADD CONSTRAINT ido_model_param_pk PRIMARY KEY (id);
ALTER TABLE ido_model_param ADD dt_created timestamp NOT NULL;
ALTER TABLE ido_model_param ADD dt_updated timestamp NULL;
ALTER TABLE ido_model_param ADD dt_deleted timestamp  NULL;
ALTER TABLE ido_model_param ADD version bigint NULL;
ALTER TABLE ido_model_param ADD COLUMN value VARCHAR(50);

CREATE SEQUENCE ido_model_param_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
    
ALTER TABLE ido_model_param ALTER COLUMN id SET DEFAULT nextval('ido_model_param_id_seq'::regclass);
