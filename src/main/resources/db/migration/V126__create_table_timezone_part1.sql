CREATE TABLE public.timezone (
	id bigserial NOT NULL,
	cod_name varchar(255) NOT NULL,
	description varchar(255) NOT NULL,
	gmt time NOT NULL,
	value int4 NOT NULL,
	CONSTRAINT timezone_pkey PRIMARY KEY (id)
);

INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Adak', 'Adak', '10:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Anchorage', 'Anchorage', '09:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Anguilla', 'Anguilla', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Antigua', 'Antigua', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Araguaina', 'Araguaina', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Araguaina', 'Argentina - Buenos Aires', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/Catamarca', 'Argentina - Catamarca', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/Cordoba', 'Argentina - Cordoba', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/Jujuy', 'Argentina - Jujuy', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/La_Rioja', 'Argentina - La Rioja', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/Mendoza', 'Argentina - Mendoza', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/Rio_Gallego', 'Argentina - Rio Gallegos', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/Salta', 'Argentina - Salta', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/San_Juan', 'Argentina - San Juan', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/Tucuman', 'Argentina - Tucuman', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Argentina/Ushuaia', 'Argentina - Ushuaia', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Aruba', 'Aruba', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Asuncion', 'Asuncion', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Atikokan', 'Atikokan', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Atka', 'Atka', '10:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Bahia', 'Bahia', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Barbados', 'Barbados', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Belem', 'Belem', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Belize', 'Belize', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Blanc-Sablon', 'Blanc-Sablon', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Boa_Vista', 'Boa Vista', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Bogota', 'Bogota', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Buenos_Aires', 'Buenos Aires', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Cambridge_Bay', 'Cambridge Bay', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Campo_Grande', 'Campo Grande', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Campo_Grande', 'Campo Grande', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Cancun', 'Cancun', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Caracas', 'Caracas', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Catamarca', 'Catamarca', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Cayenne', 'Cayenne', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Cayman', 'Cayman', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Chicago', 'Chicago', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Chihuahua', 'Chihuahua', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Coral_Harbour', 'Coral Harbour', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Cordoba', 'Cordoba', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Costa_Rica', 'Costa Rica', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Cuiaba', 'Cuiaba', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Curacao', 'Curacao', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Danmarkshavn', 'Danmarkshavn', '00:00:00', 1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Dawson', 'Dawson', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Dawson_Creek', 'Dawson Creek', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Denver', 'Denver', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Detroit', 'Detroit', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Dominica', 'Dominica', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Edmonton', 'Edmonton', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Eirunepe', 'Eirunepe', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/El_Salvador', 'El Salvador', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Ensenada', 'Ensenada', '08:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Fortaleza', 'Fortaleza', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Fort_Wayne', 'Fort Wayne', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Godthab', 'Godthab', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Goose_Bay', 'Goose Bay', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Grand_Turk', 'Grand Turk', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Grenada', 'Grenada', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Grenada', 'Grenada', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Guadeloupe', 'Guadeloupe', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Guatemala', 'Guatemala', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Guayaquil', 'Guayaquil', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Guyana', 'Guyana', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Havana', 'Havana', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Hermosillo', 'Hermosillo', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indiana/Indianapolis', 'Indiana - Indianapolis', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indiana/Knox', 'Indiana - Knox', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indiana/Marengo', 'Indiana - Marengo', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indiana/Petersburg', 'Indiana - Petersburg', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indiana/Tell_City', 'Indiana - Tell City', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indiana/Vevay', 'Indiana - Vevay', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indiana/Vincennes', 'Indiana - Vincennes', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indiana/Winamac', 'Indiana - Winamac', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Indianapolis', 'Indianapolis', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Inuvik', 'Inuvik', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Iqaluit', 'Iqaluit', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Jamaica', 'Jamaica', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Jujuy', 'Jujuy', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Juneau', 'Juneau', '09:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Kentucky/Louisville', 'Kentucky - Louisville', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Knox_IN', 'Knox IN', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/La_Paz', 'La Paz', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Lima', 'Lima', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Los_Angeles', 'Los Angeles', '08:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Louisville', 'Louisville', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Maceio', 'Maceio', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Managua', 'Managua', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Manaus', 'Manaus', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Marigot', 'Marigot', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Martinique', 'Martinique', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Matamoros', 'Matamoros', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Mazatlan', 'Mazatlan', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Mendoza', 'Mendoza', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Menominee', 'Menominee', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Merida', 'Merida', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Mexico_City', 'Mexico City', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Miquelon', 'Miquelon', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Moncton', 'Moncton', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Monterrey', 'Monterrey', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Montevideo', 'Montevideo', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Montreal', 'Montreal', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Montserrat', 'Montserrat', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Nassau', 'Nassau', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/New_York', 'New York', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Nipigon', 'Nipigon', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Nome', 'Nome', '09:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Noronha', 'Noronha', '02:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/North_Dakota/Center', 'North Dakota - Center', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/North_Dakota/New_Salem', 'North Dakota - New Salem', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Ojinaga', 'Ojinaga', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Panama', 'Panama', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Pangnirtung', 'Pangnirtung', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Paramaribo', 'Paramaribo', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Phoenix', 'Phoenix', '07:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Port-au-Prince', 'Port-au-Prince', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Porto_Acre', 'Porto Acre', '05:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Port_of_Spain', 'Port of Spain', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Porto_Velho', 'Porto Velho', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Puerto_Rico', 'Puerto Rico', '04:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Rainy_River', 'Rainy River', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Rankin_Inlet', 'Rankin Inlet', '06:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Recife', 'Recife', '03:00:00', -1);
INSERT INTO timezone (cod_name, description, gmt, value) 
VALUES('America/Regina', 'Regina', '03:00:00', -1);