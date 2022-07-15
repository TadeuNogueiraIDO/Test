ALTER TABLE local_pickup ADD COLUMN state_id int4 NULL;
ALTER TABLE local_pickup ADD CONSTRAINT fk_state_address FOREIGN KEY (state_id) REFERENCES public.state(id);