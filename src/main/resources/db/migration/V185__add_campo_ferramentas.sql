ALTER TABLE link 					ADD activated bool  NULL DEFAULT true;
ALTER TABLE contact 				ADD activated bool  NULL DEFAULT true;
ALTER TABLE business_hour       	ADD activated bool  NULL DEFAULT true;
ALTER TABLE video_banner			ADD activated bool  NULL DEFAULT true;
ALTER TABLE image_carousel 		    ADD activated bool  NULL DEFAULT true;
ALTER TABLE shop 					ADD activated bool  NULL DEFAULT true;
ALTER TABLE image_banner 			ADD activated bool  NULL DEFAULT true;
ALTER TABLE embed_html 				ADD activated bool  NULL DEFAULT true;
ALTER TABLE logo_bio 				ADD activated bool  NULL DEFAULT true;
ALTER TABLE menu_footer 			ADD activated bool  NULL DEFAULT true;
ALTER TABLE attached_pdf 			ADD activated bool  NULL DEFAULT true;
ALTER TABLE config_newsletter_form  ADD activated bool  NULL DEFAULT true;
ALTER TABLE config_faq 				ADD activated bool  NULL DEFAULT true;
ALTER TABLE config_contact_us 		ADD activated bool  NULL DEFAULT true;