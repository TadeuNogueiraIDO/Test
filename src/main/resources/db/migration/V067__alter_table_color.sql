UPDATE color SET hexadecimal_code = REPLACE (hexadecimal_code , '#', '');
UPDATE color SET "name" = REPLACE ("name" , '	', '');
UPDATE color SET hexadecimal_code = REPLACE (hexadecimal_code , '	', '');