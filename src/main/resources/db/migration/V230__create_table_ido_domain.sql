CREATE TABLE ido_domain (
	id bigserial NOT NULL,
	domain varchar NOT NULL,
    status varchar NULL,
    ido_id bigint NULL,
    dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
	"version" int4 NOT NULL,
	CONSTRAINT domain_pk PRIMARY KEY (id),
    CONSTRAINT domain_ido_fk FOREIGN KEY (ido_id) REFERENCES ido(id)
);

INSERT INTO public.tool (id,name,description,active,reuse,url,dt_created,dt_updated,dt_deleted,"version",availability,file_id,appversion,cod_name,is_listable,info_html) VALUES
	 (16,'api.model.tools.domain','api.model.tools.domaininfo',true,true,'string','2022-01-20 18:10:35.611',NULL,NULL,0,'START_FREE',849,'0.0.1','CUSTOM_DOMAIN',true,'api.model.tools.domainfaq');