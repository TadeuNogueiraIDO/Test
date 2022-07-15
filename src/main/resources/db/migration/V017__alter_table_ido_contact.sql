ALTER TABLE ido_contact ADD id bigint NOT NULL;
ALTER TABLE ido_contact ADD CONSTRAINT ido_contact_pk PRIMARY KEY (id);
ALTER TABLE ido_contact ADD dt_created timestamp NOT NULL;
ALTER TABLE ido_contact ADD dt_updated timestamp NULL;
ALTER TABLE ido_contact ADD dt_deleted timestamp  NULL;
ALTER TABLE ido_contact ADD version bigint NULL;
ALTER TABLE ido_contact ALTER COLUMN value TYPE varchar USING value::varchar;

CREATE SEQUENCE ido_contact_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
    
ALTER TABLE ido_contact ALTER COLUMN id SET DEFAULT nextval('ido_contact_id_seq'::regclass);