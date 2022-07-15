alter table country add column masked varchar(20);
update country set masked = '(00) 00000-0000' where ddi = '+55';
update country set masked = '(000) 000-0000' where ddi = '+1';