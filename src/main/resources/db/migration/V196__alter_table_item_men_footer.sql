DELETE FROM item_menu_footer;
alter table item_menu_footer drop CONSTRAINT fk_tool_id_item_menu_footer; 
ALTER TABLE item_menu_footer RENAME COLUMN tool_id TO ido_tool_id;
alter table item_menu_footer add CONSTRAINT fk_ido_tool_id_item_menu_footer FOREIGN KEY (ido_tool_id) REFERENCES ido_tools(id);