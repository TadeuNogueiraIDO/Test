
ALTER TABLE tool RENAME COLUMN file_id TO file_uuid;
ALTER TABLE tool ALTER COLUMN file_uuid TYPE varchar USING file_uuid::varchar;

UPDATE tool
SET file_uuid='2f316162-a575-4308-bae3-6ca66b392416' 
WHERE cod_name = 'IMAGEBANNER';

UPDATE tool
SET file_uuid='95aabaf3-2fd1-4edb-8f7b-63503a69bc9e' 
WHERE cod_name = 'FAQ';

UPDATE tool
SET file_uuid='5b0e5a6e-ccf8-498e-8ef6-1df03ded41bc' 
WHERE cod_name = 'CONTACTUS';

UPDATE tool
SET file_uuid='1d92c0d1-e740-40ef-8ce0-4ebd5f7ecb80' 
WHERE cod_name = 'CONTACT';

UPDATE tool
SET file_uuid='837cf188-fbb6-4353-b06e-519ab5eb91c0' 
WHERE cod_name = 'BUSINESSHOUR';

UPDATE tool
SET file_uuid='2f316162-a575-4308-bae3-6ca66b392416' 
WHERE cod_name = 'CUSTOM_DOMAIN';

UPDATE tool
SET file_uuid='968b5347-9173-4ae8-bcd0-485407849299' 
WHERE cod_name = 'MENURODAPE';

UPDATE tool
SET file_uuid='070f2e3f-6649-427f-8d23-9deb4dea19b0' 
WHERE cod_name = 'LOGOBIO';

UPDATE tool
SET file_uuid='0587262b-1df3-43bd-8d49-8d8de046b86a' 
WHERE cod_name = 'HTML';

UPDATE tool
SET file_uuid='8de9e347-0c11-4a54-b1a8-eef9bdd92791' 
WHERE cod_name = 'SHOP';

UPDATE tool
SET file_uuid='8a24881f-2c0a-4c36-bb97-cedfc80aa38c' 
WHERE cod_name = 'VIDEOBANNER';

UPDATE tool
SET file_uuid='56c33952-dda2-4e40-a0ba-ba2855ae7242' 
WHERE cod_name = 'PDF';

UPDATE tool
SET file_uuid='751a592c-2ff2-494f-ac6c-a071b10e8799' 
WHERE cod_name = 'NEWSLETTER';

UPDATE tool
SET file_uuid='307fd22b-29de-47c3-9bc2-73ee4ec93a7d' 
WHERE cod_name = 'LINK';

UPDATE tool
SET file_uuid='6a172c72-bf3e-4077-aaf8-ae3547156ce4' 
WHERE cod_name = 'IMAGECAROUSEL';