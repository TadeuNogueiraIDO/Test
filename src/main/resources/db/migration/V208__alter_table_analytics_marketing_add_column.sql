ALTER TABLE analytics_marketing ADD pixel varchar(50) NULL;

UPDATE analytics_marketing SET pixel= 'Pixel ID' WHERE id=1;
UPDATE analytics_marketing SET pixel= 'Analytics Tracking ID' WHERE id=2;
UPDATE analytics_marketing SET pixel= 'TikTok Pixel' WHERE id=3;
UPDATE analytics_marketing SET pixel= 'Pingterest Tag' WHERE id=4;

ALTER TABLE analytics_marketing ALTER COLUMN pixel SET NOT NULL;