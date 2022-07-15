CREATE TABLE public.text_font (
	id bigserial NOT NULL,
	name varchar(255) NULL,
	weight int NULL,
	style varchar(50) NULL,
	size float8 NULL,
	line_height float8 NULL,
	align varchar(255) NULL,
	CONSTRAINT text_font_pk PRIMARY KEY (id)
);

INSERT into text_font (name, weight, style, size, line_height, align) values
('Poppins', 400, 'normal', 16, 24, 'center'),
('Roboto', 500, 'normal', 16, 18.75, 'center'),
('Helvetica', 400, 'normal', 16, 18.4, 'center'),
('Montserrat', 500, 'normal', 16, 19.5, 'center'),
('Futura', 500, 'normal', 16, 21.25, 'center'),
('American Typewriter', 400, 'normal', 16, 18.46, 'center'),
('Gill Sans', 400, 'normal', 16, 18.38, 'center'),
('Rockwell', 400, 'normal', 16, 19.2, 'center'),
('Courier', 400, 'normal', 16, 18.4, 'center'),
('Geneva', 400, 'normal', 16, 21.34, 'center'),
('Lucida Grande', 700, 'normal', 16, 18.84, 'center'),
('Roboto Condensed', 400, 'normal', 16, 18.75, 'center'),
('Copperplate', 400, 'normal', 16, 16.48, 'center'),
('Lato', 400, 'normal', 16, 19.2, 'center'),
('Verdana', 400, 'normal', 16, 19.45, 'center'),
('Times', 400, 'normal', 16, 18.4, 'center');


