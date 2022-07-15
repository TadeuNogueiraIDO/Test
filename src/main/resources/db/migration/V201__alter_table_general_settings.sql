ALTER TABLE public.general_settings RENAME COLUMN receive_report_by_email TO receive_report_by_email_week;
ALTER TABLE public.general_settings ADD receive_report_by_email_month boolean NOT NULL DEFAULT false;
