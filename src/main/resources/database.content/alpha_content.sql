INSERT INTO public.pages (
    "label",
    state_delta,
    raw_content,
    status,
    created_at,
    created_by,
    modified_at
    )
VALUES
	 (
	    'default',
	    NULL,
	    'Das Orakel des Sichelmondes wachte mitten in der Nacht auf, der Atem stockte ihm.'
	    'Panisch griffen die Hände zur Seite und schmissen da eine kleine Kristallschale auf den harten Steinboden. Das Klirren des zerspringenden Glases hallte durch den großen Saal. Alarmiert stürmten einige Tempeldiener herbei. Noch immer rang das Orakel nach Luft, doch langsam kam der alte Mann wieder zu Sinnen. Kalter Schweiß glänzte im Fackellicht auf seiner Stirn und die Augenränder waren mit Tränen benetzt.'
	    'Als die kühle Abendluft wieder die müden Lungen füllte, presste er die Worte „Im Auge des Drachen liegt es! Die Asche des Königs bringt neues Leben!“ heraus. Dann sackte er wieder bewusstlos zusammen.'
        'Die Tempeldiener waren verstört. Ja, jede Eingebung des Orakels lief so ab. Am Morgen würde er sich nicht mehr daran erinnern. Nein, was sie besorgte war der Inhalt der Nachricht. Die gleichen Worte hatte das Orakel bereits in der letzten Nacht gesprochen. In der tausendjährigen Geschichte des Tempels war das noch nie passiert. Die gleiche Eingebung. Die gleichen Worte. Eilig liefen sie zum Erzpriester.'
        'Was sie nicht mehr sahen, war die dunkle Gestalt, die sich hinter einer Säule aus den Schatten löste. Sie hatte alles gehört und wusste genau, dass es ihrer Meisterin eine Menge wert sein würde diese Worte zu erfahren.',
	    'PUBLISHED',
	    now(), 'bjoern', now()
	 ),
	 (
     	    'adventure_begins',
     	    '{"items": [{"label": "gold", "change": 55, "mode":"ADD"}]}',
     	    'Du bist heute nicht vom Glück gesegnet. Deine Anreise in nach Drabruch, der größten Handelsstadt der Region, war keine Erholung und ein Gasthaus zu finden hat sich auch als schwierig herausgestellt. Die Sonne ist untergegangen, als du vor den Türen des „Geflügelter Ochse“ landest. Die Fassade ist in die Jahre gekommen, aber über der Tür verkündet ein Schild „Noch freie Betten“. Als du eintreten willst, schlägt dir die Tür entgegen und ein älterer Mann, sichtlich aufgebracht, sieht dich verdutzt an. „Lass es, geh lieber zum „Zum Platten Salamander“, raunte er dir entgegen und verschwindet in die nächste Gasse.',
     	    'PUBLISHED',
     	    now(), 'bjoern', now()
     );

INSERT INTO public.navigation_options (
    label,
    target_page,
    text,
    conditions,
    created_at,
    created_by,
    created_at
) VALUES
    (
        'nav_begin',
        'adventure_begins',
        'Das Abenteuer beginnt',
        NULL,
        now(), 'bjoern', now()
    );

INSERT INTO public.navigation_option_source (
    option_label,
    source_page,
    created_at,
    created_by,
    modified_at
) VALUES
    (
        'nav_begin',
        'default',
        now(), 'bjoern', now()
    );

