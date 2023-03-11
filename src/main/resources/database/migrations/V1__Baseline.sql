CREATE TABLE pages (
    id           serial      PRIMARY KEY,
    label        text        NOT NULL UNIQUE,
    state_delta  text,
    raw_content  text        NOT NULL,
    status       text        NOT NULL DEFAULT 'PUBLISHED',
    created_at   timestamp   NOT NULL DEFAULT now(),
    created_by   text        NULL,
    modified_at  timestamp   NOT NULL DEFAULT now(),
    modified_by  text        NULL
);

CREATE TABLE page_translations (
    id           serial      PRIMARY KEY,
    page_label      text        NOT NULL,
    locale       varchar(5)  NOT NULL,
    raw_content  text        NOT NULL,
    created_at   timestamp   NOT NULL DEFAULT now(),
    created_by   text        NULL,
    modified_at  timestamp   NOT NULL DEFAULT now(),
    modified_by  text        NULL,
    UNIQUE (page_label, locale),
    FOREIGN KEY (page_label)
          REFERENCES pages (label)
);

CREATE TABLE navigation_options (
    id              serial      PRIMARY KEY,
    label           text        NOT NULL UNIQUE,
    target_page     text        NOT NULL,
    text            text        NOT NULL,
    conditions      text,
    created_at      timestamp   NOT NULL DEFAULT now(),
    created_by      text        NULL,
    modified_at     timestamp   NOT NULL DEFAULT now(),
    modified_by     text        NULL
);

CREATE TABLE navigation_option_source (
    id              serial      PRIMARY KEY,
    option_label    text        NOT NULL,
    source_page     text        NOT NULL,
    created_at      timestamp   NOT NULL DEFAULT now(),
    created_by      text        NULL,
    modified_at     timestamp   NOT NULL DEFAULT now(),
    modified_by     text        NULL,
    UNIQUE (option_label, source_page),
    FOREIGN KEY (source_page)
          REFERENCES pages (label),
    FOREIGN KEY (option_label)
              REFERENCES navigation_options (label)
);
-- target_page is not a FK, because the option can be created
-- before the page exists. But it is still required to give
-- the label

CREATE TABLE items (
    id          serial      PRIMARY KEY,
    label       text        NOT NULL UNIQUE,
    comment     text,
    created_at  timestamp   NOT NULL DEFAULT now(),
    created_by  text        NULL,
    modified_at timestamp   NOT NULL DEFAULT now(),
    modified_by text        NULL
);

CREATE TABLE events (
    id          serial      PRIMARY KEY,
    label       text        NOT NULL UNIQUE,
    comment     text,
    created_at  timestamp   NOT NULL DEFAULT now(),
    created_by  text        NULL,
    modified_at timestamp   NOT NULL DEFAULT now(),
    modified_by text        NULL
);

CREATE TABLE states (
    id          serial      PRIMARY KEY,
    event_hash  text,
    item_hash   text,
    created_at  timestamp   NOT NULL DEFAULT now(),
    created_by  text        NULL,
    modified_at timestamp   NOT NULL DEFAULT now(),
    modified_by text        NULL,
    UNIQUE (event_hash, item_hash)
);

CREATE TABLE state_items (
    id          serial      PRIMARY KEY,
    state_id    bigint      NOT NULL,
    item_label  text        NOT NULL,
    amount      integer     NOT NULL DEFAULT 1,
    created_at  timestamp   NOT NULL DEFAULT now(),
    created_by  text        NULL,
    modified_at timestamp   NOT NULL DEFAULT now(),
    modified_by text        NULL,
    UNIQUE (state_id, item_label),
    FOREIGN KEY (state_id)
        REFERENCES states (id),
    FOREIGN KEY (item_label)
        REFERENCES items (label)
);

CREATE TABLE state_events (
    id          serial      PRIMARY KEY,
    state_id    bigint      NOT NULL,
    event_label text        NOT NULL,
    created_at  timestamp   NOT NULL DEFAULT now(),
    created_by  text        NULL,
    modified_at timestamp   NOT NULL DEFAULT now(),
    modified_by text        NULL,
    UNIQUE (state_id, event_label),
    FOREIGN KEY (state_id)
        REFERENCES states (id),
    FOREIGN KEY (event_label)
        REFERENCES events (label)
);

CREATE TABLE users (
    id              serial      PRIMARY KEY,
    last_action_at  timestamp   NOT NULL,
    created_at      timestamp   NOT NULL DEFAULT now(),
    created_by      text        NULL,
    modified_at     timestamp   NOT NULL DEFAULT now(),
    modified_by     text        NULL
);

CREATE TABLE history (
    id          serial     PRIMARY KEY,
    user_id     bigint     NOT NULL,
    page_id     bigint     NOT NULL,
    state_id    bigint     NOT NULL,
    created_at  timestamp  NOT NULL DEFAULT now(),
    FOREIGN KEY (user_id)
              REFERENCES users (id),
    FOREIGN KEY (page_id)
              REFERENCES pages (id),
    FOREIGN KEY (state_id)
              REFERENCES states (id)
);
