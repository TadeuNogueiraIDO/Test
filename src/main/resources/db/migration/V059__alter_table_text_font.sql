ALTER TABLE text_font ADD column font_family_name varchar(50); 
update text_font SET font_family_name= 'Poppins' WHERE id=1;
update text_font SET font_family_name= 'Roboto' WHERE id=2;
update text_font SET font_family_name= 'OpenSans' WHERE id=3;
update text_font SET font_family_name= 'Montserrat' WHERE id=4;
update text_font SET font_family_name= 'Quicksand' WHERE id=5;
update text_font SET font_family_name= 'MontaguSlab' WHERE id=6;
update text_font SET font_family_name= 'NotoSans' WHERE id=7;
update text_font SET font_family_name= 'Arvo' WHERE id=8;
update text_font SET font_family_name= 'Courier' WHERE id=9;
update text_font SET font_family_name= 'Cairo' WHERE id=10;
update text_font SET font_family_name= 'Cabin' WHERE id=11;
update text_font SET font_family_name= 'RobotoCondensed' WHERE id=12;
update text_font SET font_family_name= 'OverlockSC' WHERE id=13;
update text_font SET font_family_name= 'Lato' WHERE id=14;
update text_font SET font_family_name= 'Oxygen' WHERE id=15;
update text_font SET font_family_name= 'Martel' WHERE id=16;

update text_font SET name= 'Open Sans' WHERE id=3;
update text_font SET name= 'Quicksand' WHERE id=5;
update text_font SET name= 'Montagu Slab' WHERE id=6;
update text_font SET name= 'Noto Sans' WHERE id=7;
update text_font SET name= 'Arvo' WHERE id=8;
update text_font SET name= 'Cairo' WHERE id=10;
update text_font SET name= 'Cabin' WHERE id=11;
update text_font SET name= 'Overlock SC' WHERE id=13;
update text_font SET name= 'Oxygen' WHERE id=15;
update text_font SET name= 'Martel' WHERE id=16;
