ALTER TABLE link ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE faq ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE contact ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE business_hour ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE video_banner ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE image_carousel ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE shop ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE image_banner ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE newsletter_form ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE contact_us ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE embed_html ADD activated bool NOT NULL DEFAULT false;
ALTER TABLE logo_bio ADD activated bool NOT NULL DEFAULT false;

