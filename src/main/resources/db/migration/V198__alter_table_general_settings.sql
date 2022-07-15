ALTER TABLE public.general_settings ALTER COLUMN user_id SET NOT NULL;
ALTER TABLE public.general_settings ADD CONSTRAINT fk_general_settings_user_id FOREIGN KEY (user_id) REFERENCES public.user(id);