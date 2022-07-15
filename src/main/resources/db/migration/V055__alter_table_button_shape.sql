ALTER TABLE button_shape ADD column border_width float8, 
ADD column border_radius_top_left float8, 
ADD column border_radius_top_right float8, 
ADD column border_radius_bottom_left float8, 
ADD column border_radius_bottom_right float8;

UPDATE button_shape SET border_width= 1.0;
UPDATE button_shape SET border_radius_top_left= 1.0;
UPDATE button_shape SET border_radius_top_right= 1.0;
UPDATE button_shape SET border_radius_bottom_left= 1.0;
UPDATE button_shape SET border_radius_bottom_right= 1.0;