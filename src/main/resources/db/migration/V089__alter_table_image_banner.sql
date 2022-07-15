ALTER TABLE image_banner ADD dial_code varchar NULL;
ALTER TABLE image_banner ADD number bigint NULL;
ALTER TABLE image_banner ALTER COLUMN field DROP NOT NULL;