INSERT INTO public.pages ( "label", state_delta, raw_content )
VALUES (
    'default',
	NULL,
	'Eisiger Nordwind bläst dir ins Gesicht, als du die Felswand der Schlucht hinabsteigst. '
	'Der raue Stein ist mit einer dicken Eisschicht überzogen, immer wieder rutscht deine Hand auf der Suche nach Halt ab. '
	'Glücklicherweise sichert dich ein Seil, das einige Meter weiter oben von Pakrazz, einem kräftigen Zwerg, gehalten wird. '
	'Der sichere Boden ist noch immer so weit von dir entfernt, dass ein Sturz üble Folgen hätte. '
	'Was hat dich nur dazu getrieben, dich diesem Expeditionsteam anzuschließen? '
);

INSERT INTO public.navigation_options (label, target_page, text, conditions)
VALUES (
        'reason_gold_nav',
        'reason_gold',
        'Die versprochene Bezahlung war königlich!',
        NULL
    ),
    (
        'reason_honour_nav',
        'reason_honour',
        'Du kannst so eine alte Schuld begleichen.',
        NULL
    ),
    (
        'reason_adventure_nav',
        'reason_adventure',
        'Wer würde so ein Abenteuer ausschlagen?',
        NULL
    );

INSERT INTO public.navigation_option_source (option_label, source_page) VALUES
('reason_gold_nav', 'default'), ('reason_honour_nav', 'default'), ('reason_adventure_nav', 'default');


----------

INSERT INTO public.items (label, comment) VALUES ('gold', 'Goldene Münzen');
INSERT INTO public.events (label, comment) VALUES
('reason_gold_event', 'Du bist durch Gold motiviert.'),
('reason_honour_event', 'Du bist durch eine alte Schuld motiviert.'),
('reason_adventure_event', 'Du bist durch Abenteuerlust motiviert.');

INSERT INTO public.pages ( "label", state_delta, raw_content )
VALUES (
    'reason_gold',
	'{"items": [{"label": "gold", "change": 50, "mode":"ADD"}], "events": ["reason_gold_event"]}',
	'Achja, da war die gigantische Belohnung. Und die Anzahlung war auch schon einiges wert. '
	'Leider warst du schon immer sehr schlecht darin, Gold auszuschlagen. Warum muss auch nur alles so teuer sein? '
	'Der Deal ist relativ simpel - Du hilfst den beiden Forschern Selma und Elias ein altes Grab zu finden und sie bezahlen dich dafür. '
	'Du hast bereits 50 Goldmünzen bekommen, weitere 300 bekommst du in der Bank von Kungsbruck, wenn du '
	'das geheime Kennwort angibst. Welches dir Elias sagen wird, sobald das Grab gefunden ist. Einfaches Geld! '
),
(
    'reason_honour',
	'{"events": ["reason_honour_event"]}',
	'Vermutlich würdest du jetzt in irgendeiner Taverne an einem warmen Feuer sitzen, wärst du der Forscherin Selma nicht etwas '
	'schuldig. Ihr sprecht nicht darüber, was passiert ist, aber du konntest ihre Anfrage nicht ablehnen. '
	'Sie sucht mit ihrem Freund Elias nach einem vergessenen Grab. Sobald ihr es erreicht habt, gilt eure Schuld als '
	'beglichen. Außerdem winken dir 100 Goldmünzen, du gehst also nicht leer aus. '
),
(
    'reason_adventure',
	'{"events": ["reason_adventure_event"]}',
	'Du warst noch nie jemand, der dem Ruf des Abenteuers widerstehen kann. '
	'Als dich die beiden Forscher Selma und Elias angesprochen haben, um ein verschollenes Grab zu finden, '
	'wusstest du sofort, dass hier etwas großes auf dich wartet. '
	'Außerdem winken dir 100 Goldmünzen, du gehst also nicht leer aus. '
);

INSERT INTO public.navigation_options (label, target_page, text, conditions)
VALUES (
        'get_down_nav',
        'get_down',
        'Klettere weiter nach unten',
        NULL
    );

INSERT INTO public.navigation_option_source (option_label, source_page) VALUES
('get_down_nav', 'reason_gold'), ('get_down_nav', 'reason_honour'), ('get_down_nav','reason_adventure');

----------

INSERT INTO public.pages ( "label", state_delta, raw_content )
VALUES (
    'get_down',
	null,
	'Endlos scheinende Minuten später hast du es auf den sicheren Boden geschafft. '
	'Der Wind bläst unablässig Schnee in die Schlucht, so dicht, dass du die obere Kante des Felsens nicht mehr erkennen kannst. '
	'Bis auf Pakrazz ist der Rest deiner Gruppe bereits hier unten: Pakrazz Bruder Mirazz, die beiden Forscher Elias und Selma, '
	'die Nordelfin Amena und der schweigsame Mattheus. '
	'Ein lautes "Klonk!" gibt zu erkennen, dass Pakrazz das Seil gerade verankert hat. '
	'Wenige Sekunden später ist er die Wand herab geklettert, als würde er den ganzen Tag nichts anderes machen. '
	'"Vielleicht sollten wir ein Lager aufschlagen?", fragt Elias mit zitternder Lippe. Es ist offensichtlich, dass '
	'er mehr Zeit in der geschützten Bibliothek von Kungsbruck verbringt, als in der Natur.'
);

INSERT INTO public.navigation_options (label, target_page, text, conditions)
VALUES (
        'camp_for_today_nav',
        'camp_for_today',
        'Ein Lager ist eine gute Idee.',
        NULL
    ),(
        'push_forward_adventure_nav',
        'push_forward',
        'Wir sollten weiter, ein Abenteuer wartet nicht!',
        'reason_adventure_event happened'
    ),(
        'push_forward_honour_nav',
        'push_forward',
        'Wir sollten uns beeilen. Je eher wir das erledigen, desto besser.',
        'reason_honour_event happened'
    ),(
        'push_forward_gold_nav',
        'push_forward',
        'Lagern bringt kein Gold. Weiter!',
        'reason_gold_event happened'
    );

INSERT INTO public.navigation_option_source (option_label, source_page) VALUES
('camp_for_today_nav', 'get_down'), ('push_forward_adventure_nav', 'get_down'), ('push_forward_honour_nav', 'get_down'), ('push_forward_gold_nav', 'get_down');

----------

INSERT INTO public.pages ( "label", state_delta, raw_content )
VALUES (
    'camp_for_today',
	null,
	'Die anderen Gruppenmitglieder stimmen zu. Bei diesem Wetter und der einbrechenden Dunkelheit wäre es töricht weiterzugehen. '
	'Ihr habt noch etwas Feuerholz und ein paar Kohlen. Genug, um ein kleines Lagerfeuer zu errichten. '
	'Wenn die beiden Forscher richtig liegen, solltet ihr den Eingang des Grabs morgen erreichen. '
	'Das heißt, eure Vorräte werden knapp, würden aber noch für den Heimweg ausreichen. '
	'Nachdem alle Zelte aufgebaut sind, das Feuer brennt und ihr gegessen habt, gehen die anderen schlafen. '
	'Nur Pakrazz und Mirazz bleiben für die erste Wache und haben bereits ihre Würfelbecher gezückt. '
	'Heute werden wohl noch ein paar Münzen den Besitzer wechseln...'
);

INSERT INTO public.navigation_options (label, target_page, text, conditions)
VALUES (
        'go_to_sleep_nav',
        'first_night_sleep',
        'Geh auch schlafen',
        NULL
    ),(
        'casino_nav',
        'challenge_dwarfs',
        'Eine Runde Glücksspiel kann sicher nicht schaden...',
        NULL
    );

INSERT INTO public.navigation_option_source (option_label, source_page) VALUES
('go_to_sleep_nav', 'camp_for_today'), ('casino_nav', 'camp_for_today');

----------

INSERT INTO public.pages ( "label", state_delta, raw_content )
VALUES (
    'push_forward',
	null,
	'Du kannst die anderen überzeugen, dass es keine gute Idee ist, jetzt ein Lager aufzuschlagen. '
	'Wenn die beiden Forscher recht haben, ist das Grab nicht mehr weit entfernt. Und eure Vorräte werden knapp. '
	'Es reicht zwar noch für den Rückweg, aber wie Mirazz so schön sagte: "Spare in Zeiten des Überfluss, dann hast du in der Not". '
	'Wer weiß, wie die Reise weitergeht. '
	'Ihr einigt euch darauf, noch ungefähr zwei Stunden weiter zu marschieren. Dann könnt ihr immernoch ein Lager aufstellen.'
);
-- TODO: Optionen.