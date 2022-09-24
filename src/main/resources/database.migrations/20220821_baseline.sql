CREATE TABLE pages (
    id           serial      PRIMARY KEY,
    label        text        NOT NULL UNIQUE,
    state_delta  json,
    raw_content  text        NOT NULL,
    created_at   timestamp   NOT NULL,
    created_by   text        NOT NULL,
    modified_at  timestamp   NOT NULL,
    modified_by  text   NOT NULL
);

CREATE TABLE page_translations (
    id           serial      PRIMARY KEY,
    page_id      integer     NOT NULL,
    locale       varchar(5)  NOT NULL,
    raw_content  text        NOT NULL,
    created_at   timestamp   NOT NULL,
    created_by   text        NOT NULL,
    modified_at  timestamp   NOT NULL,
    modified_by  text   NOT NULL,
    UNIQUE (page_id, locale),
    FOREIGN KEY (page_id)
          REFERENCES pages (id)
);

CREATE TABLE navigation_options (
    id              serial      PRIMARY KEY,
    source_page     text        NOT NULL,
    target_page     text        NOT NULL,
    text            text        NOT NULL,
    conditions      json,
    created_at      timestamp   NOT NULL,
    created_by      text        NOT NULL,
    modified_at     timestamp   NOT NULL,
    modified_by     text        NOT NULL,
    FOREIGN KEY (source_page)
          REFERENCES pages (label),
    FOREIGN KEY (target_page)
          REFERENCES pages (label)
);

CREATE TABLE items (
    id          text        PRIMARY KEY,
    comment     text,
    created_at  timestamp   NOT NULL,
    created_by  text        NOT NULL,
    modified_at timestamp   NOT NULL,
    modified_by text        NOT NULL
);

CREATE TABLE events (
    id          text        PRIMARY KEY,
    comment     text,
    created_at  timestamp   NOT NULL,
    created_by  text        NOT NULL,
    modified_at timestamp   NOT NULL,
    modified_by text        NOT NULL
);

CREATE TABLE states (
    id          serial      PRIMARY KEY,
    event_hash  text        NOT NULL,
    item_hash   text        NOT NULL,
    created_at  timestamp   NOT NULL,
    created_by  text        NOT NULL,
    modified_at timestamp   NOT NULL,
    modified_by text        NOT NULL,
    UNIQUE (event_hash, item_hash)
);

CREATE TABLE state_items (
    id          serial      PRIMARY KEY,
    state_id    integer     NOT NULL,
    item_id     text        NOT NULL,
    amount      integer     NOT NULL DEFAULT 1,
    created_at  timestamp   NOT NULL,
    created_by  text        NOT NULL,
    modified_at timestamp   NOT NULL,
    modified_by text        NOT NULL,
    UNIQUE (state_id, item_id)
);

CREATE TABLE state_events (
    id          serial      PRIMARY KEY,
    state_id    integer     NOT NULL,
    event_id    text        NOT NULL,
    created_at  timestamp   NOT NULL,
    created_by  text        NOT NULL,
    modified_at timestamp   NOT NULL,
    modified_by text        NOT NULL,
    UNIQUE (state_id, event_id)
);

CREATE TABLE users (
    id              serial      PRIMARY KEY,
    last_action_at  timestamp   NOT NULL,
    created_at      timestamp   NOT NULL,
    created_by      text        NOT NULL,
    modified_at     timestamp   NOT NULL,
    modified_by     text        NOT NULL
);

CREATE TABLE history (
    id          serial      PRIMARY KEY,
    user_id     integer     NOT NULL,
    page_id     integer     NOT NULL,
    state_id    integer     NOT NULL,
    created_at  timestamp   NOT NULL,
    FOREIGN KEY (user_id)
              REFERENCES users (id),
    FOREIGN KEY (page_id)
              REFERENCES pages (id),
    FOREIGN KEY (state_id)
              REFERENCES states (id)
);
