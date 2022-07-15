ALTER TABLE link DROP COLUMN icon;
ALTER TABLE link ADD COLUMN url_icon varchar(150);
ALTER TABLE link ADD COLUMN typeicon int8;
ALTER TABLE link ADD COLUMN url varchar(150);