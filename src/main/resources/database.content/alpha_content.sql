INSERT INTO public.pages ("label",state_delta,raw_content,status,created_at,created_by,modified_at,modified_by)
VALUES
	 ('default',NULL,'Default_text_with_some_noise','PUBLISHED',now(),'admin',now(),NULL),
	 ('second_page','{"items":[{"label":"item","change":2,"mode":"ADD"}],"events":["event"]}','Second page content','PUBLISHED',now(),'admin',now(),NULL)
	 ;
