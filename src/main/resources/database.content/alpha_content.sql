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
	    'Du öffnest die Augen und findest dich in einem dunklen Raum wieder. '
	    'Du bist desorientiert und hast keine Ahnung, wie du hierher gekommen bist. '
	    'Deine Hände fühlen sich feucht und klebrig an. '
	    'Als sich deine Augen an das schwache Licht gewöhnen, bemerkst du, dass du in einer Art Kellerverlies gefangen bist. '
	    'Was möchtest du als Nächstes tun? ',
	    'PUBLISHED',
	    now(), 'bjoern', now()
	 ),
	 (
     	'relax_and_wait',
     	NULL,
     	'Du beschließt, ruhig zu bleiben und auf Rettung zu warten. '
     	'Du legst dich auf den kalten, feuchten Boden und versuchst, dich zu entspannen. '
     	'Du hoffst, dass jemand bald kommt, um dich zu retten. '
        'Die Zeit vergeht und du beginnst, unruhig zu werden. Es fühlt sich an, als ob du schon seit Stunden hier bist. '
        'Du versuchst, dich zu erinnern, wie du hierher gekommen bist, aber alles, was dir einfällt, ist ein plötzlicher Schlag auf den Kopf, als du durch die Straßen gelaufen bist.'
        'Plötzlich hörst du ein Geräusch. '
        'Es ist ein dumpfes Knallen, das von oben kommt. Dann hörst du Schritte und eine Tür, die aufgeschlossen wird. '
        'Du bist erleichtert und hoffst, dass es deine Rettung ist. '
        'Was möchtest du als Nächstes tun?',
     	'PUBLISHED',
     	now(), 'bjoern', now()
     ),
	 (
     	'call_for_help',
     	NULL,
     	'Du versuchst so laut wie möglich zu schreien. "Hallo? Kann mir jemand helfen?" rufst du. '
        'Nach einem Moment der Ruhe hörst du Schritte, die sich dir nähern. Dann eine Stimme, die sagt: "Hey, bist du in Ordnung? Was machst du hier unten?" '
        'Du antwortest erleichtert: "Ich wurde hier eingesperrt. Ich weiß nicht, wie ich hierher gekommen bin!" '
        'Die Person kommt näher und der Schein einer Fackel beleuchtet dein Gesicht. Es ist ein junger Mann in abgenutzer Kleidung. '
        'Er sieht besorgt aus und sagt: "Keine Sorge, ich werde dir helfen, hier rauszukommen. '
        'Aber wir müssen vorsichtig sein, wir wissen nicht, wer uns hier eingesperrt hat!" '
        'Mit einem leisen Klimpern öffnet er das Schloss deiner Zelle. '
        'Was möchtest du als Nächstes tun?',
     	'PUBLISHED',
     	now(), 'bjoern', now()
     ),
	 (
     	'who_are_you',
     	NULL,
     	'Du kannst das Gefühl von Misstrauen nicht ganz abschütteln und fragst deinen Retter ganz direkt, wer er ist. '
     	'Der junge Mann murmelt halblaut vor sich hin, dass er nicht weiß, wo ihr seid - '
     	'aber er wurde ebenfalls eingesperrt und konnte sich durch Glück befreien. '
        'Was möchtest du als Nächstes tun?',
     	'PUBLISHED',
     	now(), 'bjoern', now()
     ),
	 (
     	'go_solo',
     	NULL,
     	'Das behagt dir alles nicht. Bevor der junge Mann reagieren kann, hast du dich an ihm vorbei in den Gang '
     	'geschoben und rennst. Du hörst ihn noch kurz rufen, doch schon zwei Ecken weiter hast du ihn abgeschüttelt. '
     	'Als du kurz stehen bleibst, erkennst du neben mehreren weiteren Zellen eine Treppe, die aufwärts führt.'
        'Was möchtest du als Nächstes tun?',
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
    modified_at
) VALUES
    (
        'call_for_help_nav',
        'call_for_help',
        'Du rufst um Hilfe und hoffst, dass dich jemand hören kann.',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'investigate_cell_nav',
        'investigate_cell',
        'Du untersuchst deine Umgebung und versuchst, einen Ausweg zu finden.',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'relax_and_wait_nav',
        'relax_and_wait',
        'Du entspannst dich und wartest, in der Hoffnung, dass jemand dich bald rettet.',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'hide_nav',
        'hide_and_wait',
        'Du versteckst dich und beobachtest, wer hereinkommt, um sicherzustellen, dass es sich um einen Retter handelt.',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'run_and_escape_nav',
        'run_and_escape',
        'Du stehst auf und rennst zur Tür, um zu fliehen.',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'follow_nav',
        'follow_young_mane',
        'Du folgst dem Mann und lässt dir von ihm helfen.',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'who_are_you_nav',
        'who_are_you',
        'Du fragst den Mann, wer er ist und was er hier macht, bevor du ihm folgst.',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'go_solo_nav',
        'go_solo',
        'Du entscheidest dich, allein zu gehen und suchst nach einem anderen Weg, um aus dem Verlies zu entkommen.',
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
        'follow_nav',
        'call_for_help',
        now(), 'bjoern', now()
    ),
    (
        'who_are_you_nav',
        'call_for_help',
        now(), 'bjoern', now()
    ),
    (
        'go_solo_nav',
        'call_for_help',
        now(), 'bjoern', now()
    ),
    (
        'follow_nav',
        'who_are_you',
        now(), 'bjoern', now()
    ),
    (
        'go_solo_nav',
        'who_are_you',
        now(), 'bjoern', now()
    ),
    (
        'call_for_help_nav',
        'default',
        now(), 'bjoern', now()
    ),
    (
        'call_for_help_nav',
        'relax_and_wait',
        now(), 'bjoern', now()
    ),
    (
        'investigate_cell_nav',
        'default',
        now(), 'bjoern', now()
    ),
    (
        'relax_and_wait_nav',
        'default',
        now(), 'bjoern', now()
    ),
    (
        'hide_nav',
        'relax_and_wait',
        now(), 'bjoern', now()
    ),
    (
        'run_and_escape_nav',
        'relax_and_wait',
        now(), 'bjoern', now()
    );

