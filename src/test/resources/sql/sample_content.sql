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
    );
