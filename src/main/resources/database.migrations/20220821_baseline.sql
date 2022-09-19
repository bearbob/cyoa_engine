CREATE TABLE pages (
    id           serial      PRIMARY KEY,
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
    source_page_id  integer     NOT NULL,
    target_page_id  integer     NOT NULL,
    text            text        NOT NULL,
    conditions      json,
    created_at      timestamp   NOT NULL,
    created_by      text        NOT NULL,
    modified_at     timestamp   NOT NULL,
    modified_by     text        NOT NULL,
    FOREIGN KEY (source_page_id)
          REFERENCES pages (id),
    FOREIGN KEY (target_page_id)
          REFERENCES pages (id)
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

CREATE TABLE state (
    id          text    PRIMARY KEY,
    event_hash  text    NOT NULL,
    item_hash   text    NOT NULL
);


