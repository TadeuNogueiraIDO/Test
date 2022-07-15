ALTER TABLE shop ADD column ido_id bigint NOT NULL,
add CONSTRAINT fk_shop_ido FOREIGN KEY (ido_id) references ido(id);