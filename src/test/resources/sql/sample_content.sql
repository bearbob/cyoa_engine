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
	    'This is the default page for tests',
	    'PUBLISHED',
	    now(), 'bjoern', now()
	 ),
	 (
     	    'adventure_begins',
     	    '{"items": [{"label": "gold", "change": 50, "mode":"ADD"}]}',
     	    'This is the first page with items',
     	    'PUBLISHED',
     	    now(), 'bjoern', now()
     ),
	 (
     	    'gold_lost',
     	    '{"items": [{"label": "gold", "change": 25, "mode":"REMOVE"}]}',
     	    '25 Gold lost',
     	    'PUBLISHED',
     	    now(), 'bjoern', now()
     ),
	 (
     	    'orphan_page',
     	    NULL,
     	    'This is a page that has no links and is not linked to.',
     	    'PUBLISHED',
     	    now(), 'bjoern', now()
     ),
     (
     	    'crossroads',
     	    NULL,
     	    'There are multiple pages available from here on. One is only available when you have enough gold.',
     	    'PUBLISHED',
     	    now(), 'bjoern', now()
     ),
     (
     	    'road_a',
     	    NULL,
     	    'Road to a city',
     	    'PUBLISHED',
     	    now(), 'bjoern', now()
     ),
     (
     	    'road_b',
     	    NULL,
     	    'Road to a castle',
     	    'PUBLISHED',
     	    now(), 'bjoern', now()
     ),
     (
     	    'road_c',
     	    NULL,
     	    'Road to a tower',
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
        'start',
        'adventure_begins',
        'To adventure!',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'buy',
        'gold_lost',
        'Buy sword',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'to_xroads',
        'crossroads',
        'Follow the road',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'follow_a',
        'road_a',
        'To the city',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'follow_b',
        'road_b',
        'To the castle',
        NULL,
        now(), 'bjoern', now()
    ),
    (
        'follow_c',
        'road_c',
        'To the tower',
        NULL, -- Add a condition that this is only available when you have more than 30 gold
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
        'start',
        'default',
        now(), 'bjoern', now()
    ),
    (
        'buy',
        'adventure_begins',
        now(), 'bjoern', now()
    ),
    (
        'to_xroads',
        'adventure_begins',
        now(), 'bjoern', now()
    ),
    (
        'to_xroads',
        'gold_lost',
        now(), 'bjoern', now()
    ),
    (
        'follow_a',
        'crossroads',
        now(), 'bjoern', now()
    ),
    (
        'follow_b',
        'crossroads',
        now(), 'bjoern', now()
    ),
    (
        'follow_b',
        'crossroads',
        now(), 'bjoern', now()
    );
