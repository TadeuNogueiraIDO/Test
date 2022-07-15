delete from appearance_button; 
delete from appearance_cards;
delete from appearance_text; 

/*appearance button*/
alter table appearance_button drop constraint if exists fk_border_color_id ;
alter table appearance_button drop constraint if exists fk_button_color_id;
alter table appearance_button drop constraint if exists fk_font_color_id;

alter table appearance_button drop column if exists button_color_id;
alter table appearance_button drop column if exists border_color_id;
alter table appearance_button drop column if exists font_color_id;

alter table appearance_button add column if not exists button_color varchar(20);
alter table appearance_button add column if not exists border_color varchar(20);
alter table appearance_button add column if not exists font_color varchar(20);


/*appearance cards*/
alter table appearance_cards drop constraint if exists fk_border_color_id ;
alter table appearance_cards drop constraint if exists fk_card_color_id;
alter table appearance_cards drop constraint if exists fk_font_color_id;

alter table appearance_cards drop column if exists border_color_id;
alter table appearance_cards drop column if exists card_color_id;
alter table appearance_cards drop column if exists font_color_id;

alter table appearance_cards add column if not exists border_color varchar(20);
alter table appearance_cards add column if not exists card_color varchar(20);
alter table appearance_cards add column if not exists font_color varchar(20);


/*appearance text*/
alter table appearance_text drop constraint if exists fk_background_color_id ;
alter table appearance_text drop constraint if exists fk_font_color_id;

alter table appearance_text drop column if exists background_color_id;
alter table appearance_text drop column if exists font_color_id;

alter table appearance_text add column if not exists background_color varchar(20);
alter table appearance_text add column if not exists font_color varchar(20);

/*product color*/
alter table product_variation drop column if exists product_color_id ;
alter table product_variation add column if not exists product_color varchar(20);

/*configuracoes de entrega*/
delete from own_shipping_variation;
delete from own_shipping;
delete from local_pickup; 
delete from post_office_shipping;
delete from digital_delivery; 
delete from shipping_settings; 

/*shop*/
delete from payment_setup;
delete from product_variation; 
delete from shop_product;
delete from shop_category; 
delete from shop;
delete from activity_shop; 

/*pdf*/
delete from attached_pdf_leads;
delete from attached_pdf; 

/*business hour*/
delete from business_hour_alternative_schedule_schedule;
delete from business_hour_alternative_schedule;
delete from business_hour_day_schedule;
delete from business_hour_day; 
delete from business_hour_schedule;

/*contact us*/
delete from contact_us;
delete from config_contact_us; 

/*newsletter*/
delete from newsletter_form;
delete from config_newsletter_form; 

/*faq*/
delete from faq;
delete from config_faq;

/*link*/
delete from link_button_animation;
delete from link;

/*ido*/
delete from ido_category;
delete from ido_contact;
delete from ido_model_param; 

/*banner*/
delete from image_banner; 
delete from video_banner;
delete from image_carousel_item; 
delete from image_carousel_item_detail;
delete from image_carousel; 

/*ido tools*/
delete from ido_tools_position;
delete from ido_tools; 

/*ido notification*/
delete from notification;

/*ido*/
delete from ido; 






























