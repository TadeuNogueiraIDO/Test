ALTER TABLE tool ADD column IF NOT EXISTS appversion varchar(50);
ALTER TABLE tool ADD column IF NOT EXISTS cod_name varchar(50) UNIQUE; 

