ALTER TABLE activity ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE activity SET dt_updated = null;

ALTER TABLE appearance_button ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE appearance_button SET dt_updated = null;

ALTER TABLE attached_pdf ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE attached_pdf SET dt_updated = null;

ALTER TABLE attached_pdf_leads ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE attached_pdf_leads SET dt_updated = null;

ALTER TABLE business ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE business SET dt_updated = null;

ALTER TABLE business_hour ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE business_hour SET dt_updated = null;

ALTER TABLE business_hour_alternative_schedule ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE business_hour_alternative_schedule SET dt_updated = null;

ALTER TABLE business_hour_day ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE business_hour_day SET dt_updated = null;

ALTER TABLE business_hour_schedule ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE business_hour_schedule SET dt_updated = null;

ALTER TABLE button_animation ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE button_animation SET dt_updated = null;

ALTER TABLE category ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE category SET dt_updated = null;

ALTER TABLE config_contact_us ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE config_contact_us SET dt_updated = null;

ALTER TABLE contact ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE contact SET dt_updated = null;

ALTER TABLE contact_us ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE contact_us SET dt_updated = null;

ALTER TABLE customer ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE customer SET dt_updated = null;

ALTER TABLE general_settings ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE general_settings SET dt_updated = null;

ALTER TABLE ido ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE ido SET dt_updated = null;

ALTER TABLE ido_contact ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE ido_contact SET dt_updated = null;

ALTER TABLE ido_model_param ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE ido_model_param SET dt_updated = null;

ALTER TABLE ido_tools ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE ido_tools SET dt_updated = null;

ALTER TABLE image_banner ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE image_banner SET dt_updated = null;

ALTER TABLE link ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE link SET dt_updated = null;

ALTER TABLE model_param ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE model_param SET dt_updated = null;

ALTER TABLE newsletter_form ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE newsletter_form SET dt_updated = null;

ALTER TABLE pre_def_model ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE pre_def_model SET dt_updated = null;

ALTER TABLE pre_def_model_param ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE pre_def_model_param SET dt_updated = null;

ALTER TABLE tool ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE tool SET dt_updated = null;

ALTER TABLE "user" ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE "user" SET dt_updated = null;

ALTER TABLE video_banner ALTER COLUMN dt_updated DROP NOT NULL;
UPDATE video_banner SET dt_updated = null;