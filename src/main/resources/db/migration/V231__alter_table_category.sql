ALTER TABLE public.category ADD "filter" varchar(100) NULL;


UPDATE category SET filter = 'gym academia gimnasia' WHERE id = 1;
UPDATE category SET filter = 'agro' WHERE id = 2;
UPDATE category SET filter = 'attorney advogado abogado' WHERE id = 3;
UPDATE category SET filter = 'foods alimentos' WHERE id = 4;
UPDATE category SET filter = 'animals animais animales' WHERE id = 5;
UPDATE category SET filter = 'babies bebês' WHERE id = 6;
UPDATE category SET filter = 'beauty beleza belleza' WHERE id = 7;
UPDATE category SET filter = 'beverages bebidas' WHERE id = 8;
UPDATE category SET filter = 'toys brinquedo juguetes' WHERE id = 9;
UPDATE category SET filter = 'home casa hogar' WHERE id = 10;
UPDATE category SET filter = 'phones telefones Los telefonos' WHERE id = 11;
UPDATE category SET filter = 'consult consultar' WHERE id = 12;
UPDATE category SET filter = 'construction construção construcción' WHERE id = 13;
UPDATE category SET filter = 'accounting contabilidade contabilidad' WHERE id = 14;
UPDATE category SET filter = 'dance dança danza' WHERE id = 15;
UPDATE category SET filter = 'education educação educación' WHERE id = 16;
UPDATE category SET filter = 'appliance utensílio aparato' WHERE id = 17;
UPDATE category SET filter = 'eletronic eletrônico electrónico' WHERE id = 18;
UPDATE category SET filter = 'sport esporte deporte' WHERE id = 19;
UPDATE category SET filter = 'event evento' WHERE id = 20;
UPDATE category SET filter = 'restaurant restaurante ' WHERE id = 21;
UPDATE category SET filter = 'graphics gráficos' WHERE id = 22;
UPDATE category SET filter = 'hobbies aficiones' WHERE id = 23;

ALTER TABLE tool ADD "filter" varchar(100) NULL;
UPDATE tool SET filter = 'link enlace' WHERE cod_name = 'LINK';
UPDATE tool SET filter = 'business hour hora de trabalho hora de trabajo' WHERE cod_name = 'BUSINESSHOUR';
UPDATE tool SET filter = 'contact contato contacto' WHERE cod_name = 'CONTACT';
UPDATE tool SET filter = 'contactus contatenos contacta con nosotros' WHERE cod_name = 'CONTACTUS';
UPDATE tool SET filter = 'faq perguntas frequentes preguntas más frecuentes' WHERE cod_name = 'FAQ';
UPDATE tool SET filter = 'image banner faixa de imagem pancarta de imagen' WHERE cod_name = 'IMAGEBANNER';
UPDATE tool SET filter = 'image carousel carrossel de imagens carrusel de imágenes' WHERE cod_name = 'IMAGECAROUSEL';
UPDATE tool SET filter = ' news letter boletim de notícias boletin informativo' WHERE cod_name = 'NEWSLETTER';
UPDATE tool SET filter = 'pdf' WHERE cod_name = 'PDF';
UPDATE tool SET filter = 'video banner faixa de vídeo pancarta de vídeo' WHERE cod_name = 'VIDEOBANNER';
UPDATE tool SET filter = 'shop loja tienda' WHERE cod_name = 'SHOP';
UPDATE tool SET filter = 'html' WHERE cod_name = 'HTML';
UPDATE tool SET filter = 'logo bio bio do logotipo biografía del logotipo' WHERE cod_name = 'LOGOBIO';
UPDATE tool SET filter = 'custom domain  domínio personalizado dominio personalizado ' WHERE cod_name = 'CUSTOM_DOMAIN';
UPDATE tool SET filter = 'menu rodape menú rodado' WHERE cod_name = 'MENURODAPE';


