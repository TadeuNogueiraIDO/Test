ALTER TABLE ido DROP COLUMN country;
ALTER TABLE ido ADD COLUMN country_id int8;

ALTER TABLE ido 
ADD CONSTRAINT ido_country_fk 
FOREIGN KEY (country_id) 
REFERENCES country (id);


