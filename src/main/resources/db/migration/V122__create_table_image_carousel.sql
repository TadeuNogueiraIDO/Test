CREATE TABLE image_carousel (
	id bigserial NOT NULL,
	title varchar(255) NOT NULL,
    show_title varchar(255) NOT NULL,
    image_format bigint NOT NULL,
	ido_id bigint NOT NULL,
	version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,	
	CONSTRAINT image_carousel_pkey PRIMARY KEY (id),
	CONSTRAINT image_carousel_ido_fk FOREIGN KEY (ido_id) REFERENCES ido(id)
);

CREATE TABLE image_carousel_item_detail (
	id bigserial NOT NULL,
	title varchar(255) NOT NULL,
	price float8 NULL,
    description varchar NOT NULL,
	version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,
    
	CONSTRAINT image_carousel_item_detail_pkey PRIMARY KEY (id)

);

CREATE TABLE image_carousel_item (
	id bigserial NOT NULL,
	image_id bigint NOT NULL,
	action bigint NOT NULL,
    action_field varchar NOT NULL,
    image_carousel_id bigint NOT NULL,
	image_carousel_item_detail_id bigint NOT NULL,
	version int4 NOT NULL,
	dt_created timestamp NOT NULL,
	dt_deleted timestamp NULL,
	dt_updated timestamp NULL,	
	
	CONSTRAINT image_carousel_item_pkey PRIMARY KEY (id),
	CONSTRAINT image_carousel_image_carousel_item_fk FOREIGN KEY (image_carousel_id) REFERENCES image_carousel(id),
	CONSTRAINT image_carousel_item_detail_fk FOREIGN KEY (image_carousel_item_detail_id) REFERENCES image_carousel_item_detail(id)	
);

