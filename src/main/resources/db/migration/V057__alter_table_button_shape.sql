update button_shape 
SET border_radius_top_left= 0, border_radius_top_right= 0, border_radius_bottom_left= 0, border_radius_bottom_right= 0
WHERE name='Square';
update button_shape 
SET border_radius_top_left= 200, border_radius_top_right= 200, border_radius_bottom_left= 200, border_radius_bottom_right= 200
WHERE name='Round';
update button_shape 
SET border_radius_top_left= 8, border_radius_top_right= 8, border_radius_bottom_left= 8, border_radius_bottom_right= 8
WHERE name='Rounded';