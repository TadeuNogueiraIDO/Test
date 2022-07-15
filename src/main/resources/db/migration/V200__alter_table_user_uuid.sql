ALTER TABLE "user" ADD uuid varchar (100) NULL;

UPDATE "user" set uuid=concat('USR-',uuid_in(overlay(overlay(md5(random()::text || ':' || clock_timestamp()::text) placing '4' from 13) placing to_hex(floor(random()*(11-8+1) + 8)::int)::text from 17)::cstring));

ALTER TABLE "user" ALTER COLUMN uuid SET NOT NULL;