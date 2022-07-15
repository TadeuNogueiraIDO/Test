create table wallpaper_color(
	id bigserial NOT NULL,
	name varchar(100) NOT NULL,
	color varchar(20) NOT NULL,
	CONSTRAINT pk_wallpaper_color PRIMARY KEY (id)
);

create table wallpaper_color_gradient(
	id bigserial NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT pk_wallpaper_color_gradient_pk PRIMARY KEY (id)
);

create table wallpaper_color_gradient_step(
	id bigserial NOT NULL,
	wallpaper_color_gradient_id bigint NOT NULL,
	alignment_start varchar(100) NOT NULL,
	alignment_end varchar(100) NOT NULL,
	CONSTRAINT pk_wallpaper_color_gradient_step PRIMARY KEY (id),
	CONSTRAINT fk_wallpaper_color_gradient_step_wallpaper_color_gradient FOREIGN KEY (wallpaper_color_gradient_id) REFERENCES wallpaper_color_gradient(id)
);

create table wallpaper_gallery(
	id bigserial NOT NULL,
	file_id bigint NOT NULL,
	CONSTRAINT pk_wallpaper_gallery PRIMARY KEY (id)
);