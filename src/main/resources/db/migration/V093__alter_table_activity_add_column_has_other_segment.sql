ALTER TABLE activity ADD COLUMN has_other_segment boolean default FALSE;
update activity set has_other_segment = true where segment like 'Outros';
