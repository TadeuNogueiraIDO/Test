ALTER TABLE public.activity ADD COLUMN filter varchar(100);

update public.activity set filter = 'Academia e Esportes Gimnasio y Deportes Gym and Sports' where segment = 'api.ido.activities.gymandsports';
update public.activity set filter = 'Advogados Abogados Lawyers' where segment = 'api.ido.activities.lawyers';
update public.activity set filter = 'Agro agro' where segment = 'api.ido.activities.agro';
update public.activity set filter = 'Alimentos y bebidas Alimentos e bebidas Food and beverages' where segment = 'api.ido.activities.foodanddrinks';
update public.activity set filter = 'Animals Animales Animais' where segment = 'api.ido.activities.animals';
update public.activity set filter = 'Bebés babies Bebês' where segment = 'api.ido.activities.babies';
update public.activity set filter = 'Beleza e cuidado pessoal Beauty and personal care Belleza y cuidado personal' where segment = 'api.ido.activities.beautyandpersonalcare';
update public.activity set filter = 'Casa e jardim  Casa y jardín House and garden' where segment = 'api.ido.activities.houseandgarden';
update public.activity set filter = 'Brinquedos e Hobbies Juguetes y Pasatiempos Toys and Hobbies' where segment = 'api.ido.activities.toysandhobbies';
update public.activity set filter = 'Celulares y teléfono Celulares e telefone Cell phones and telephone' where segment = 'api.ido.activities.cellphonesandtelephone';
update public.activity set filter = 'construção construction construcción' where segment = 'api.ido.activities.construction';
update public.activity set filter = 'Eletrodomésticos Electrodomésticos Home Appliances' where segment = 'api.ido.activities.homeappliances';
update public.activity set filter = 'Eletrônicos Electrónica Electronics' where segment = 'api.ido.activities.electronics';
update public.activity set filter = 'jogos games Juegos' where segment = 'api.ido.activities.games';
update public.activity set filter = 'Informática Informática Computing' where segment = 'api.ido.activities.computing';
update public.activity set filter = 'Mercado Market' where segment = 'api.ido.activities.market';
update public.activity set filter = 'Moda e vestuário Moda y ropa Fashion and Clothing' where segment = 'api.ido.activities.fashionandclothing';
update public.activity set filter = 'Saúde Salud Health' where segment = 'api.ido.activities.health';
update public.activity set filter = 'Consultoria Consultoría Consultancy' where segment = 'api.ido.activities.consultancy';
update public.activity set filter = 'Contabilidade Contabilidad Accounting' where segment = 'api.ido.activities.accounting';
update public.activity set filter = 'gastronomy Gastronomia Gastronomía' where segment = 'api.ido.activities.gastronomy';
update public.activity set filter = 'Gráficas e Impressão Gráficos e impresión Graphics and Printing' where segment = 'api.ido.activities.graphicsandprinting';
update public.activity set filter = 'Educación Educação education' where segment = 'api.ido.activities.education';
update public.activity set filter = 'Marketing e Internet Marketing and Internet Marketing y Internet' where segment = 'api.ido.activities.marketingandinternet';
update public.activity set filter = 'Odontologia dentistry Odontología' where segment = 'api.ido.activities.dentistry';
update public.activity set filter = 'Técnico de soporte Técnico de suporte Support Technician' where segment = 'api.ido.activities.supporttechnician';
update public.activity set filter = 'Viagens e Turismo Viajes y Turismo Travel and Tourism' where segment = 'api.ido.activities.travelandtourism';
update public.activity set filter = 'Veículos e Transportes Vehículos y Transporte Vehicles and Transport' where segment = 'api.ido.activities.vehiclesandtransport';
update public.activity set filter = 'Bebida alcoólica Bebida alcohólica Alcoholic beverage' where segment = 'api.ido.activities.alcoholicbeverage';
update public.activity set filter = 'Instrumentos musicais Instrumentos musicales Musical instruments' where segment = 'api.ido.activities.musicalinstruments';
update public.activity set filter = 'Festa e eventos Fiesta y eventos Party and events' where segment = 'api.ido.activities.partyandevents';
update public.activity set filter = 'Entradas Entries' where segment = 'api.ido.activities.appetizer';
update public.activity set filter = 'Cama, mesa e banho Cama, mesa y baño Bed, table and bath' where segment = 'api.ido.activities.bed.tableandbath';
update public.activity set filter = 'Entrega e alimentos Entrega y comida Delivery and food' where segment = 'api.ido.activities.deliveryandfood';
update public.activity set filter = 'others outros Otros' where segment = 'api.ido.activities.others';
update public.activity set filter = 'Profissional liberal Profesional autónomo elf-employed professional' where segment = 'api.ido.activities.self-employedprofessional';